package com.kponomarev.databasetodo.ui.feature_add_task.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kponomarev.databasetodo.R
import com.kponomarev.databasetodo.databinding.FragmentTaskDetailsBinding
import com.kponomarev.databasetodo.ui.feature_add_task.presentation.AddTaskState
import com.kponomarev.databasetodo.ui.feature_add_task.presentation.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.LazyThreadSafetyMode.NONE

@AndroidEntryPoint
class TaskDetailsFragment : Fragment(R.layout.fragment_task_details) {

    companion object {
        private const val TASK_ID = "taskId"

        @JvmStatic
        fun createFromScratch(): Fragment {
            return TaskDetailsFragment()
        }

        @JvmStatic
        fun edit(taskId: Int): Fragment {
            return TaskDetailsFragment().apply {
                this.arguments = bundleOf(TASK_ID to taskId)
            }
        }
    }

    private var taskId: Int? = null
    private val viewModel: AddTaskViewModel by viewModels()
    private val binding by viewBinding(FragmentTaskDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskId = arguments?.getInt(TASK_ID)
        initViews()
        viewLifecycleOwner.lifecycleScope.launch {
            val taskId = taskId
            if (taskId != null) {
                viewModel.prefillTask(taskId)
            }
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest(::render)
            }
        }
    }

    private fun initViews() {
        binding.taskDetails.setText(if (taskId != null) R.string.new_task else R.string.edit_task)
        binding.taskName.doAfterTextChanged {
            viewModel.modelChanged(it.toString())
            binding.taskName.setSelection(it.toString().length)
        }
        binding.saveButton.setText(
            if (taskId != null) {
                R.string.save_changes_button_text
            } else {
                R.string.add_button_text
            }
        )
        binding.saveButton.setOnClickListener {
            viewModel.saveChanges()
            parentFragmentManager.popBackStack()
        }
    }

    private fun render(addTaskState: AddTaskState) {

        binding.taskName.setText(addTaskState.task.taskName)
    }
}
