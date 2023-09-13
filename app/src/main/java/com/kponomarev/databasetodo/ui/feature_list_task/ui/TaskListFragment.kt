package com.kponomarev.databasetodo.ui.feature_list_task.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kponomarev.databasetodo.R
import com.kponomarev.databasetodo.databinding.FragmentTaskListBinding
import com.kponomarev.databasetodo.ui.feature_add_task.ui.TaskDetailsFragment
import com.kponomarev.databasetodo.ui.feature_list_task.presentation.TaskListViewModel
import com.kponomarev.databasetodo.ui.feature_list_task.presentation.UiStateMapper
import com.kponomarev.databasetodo.ui.feature_list_task.presentation.model.TaskUi
import com.kponomarev.databasetodo.ui.feature_list_task.ui.recycler.TaskAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskListFragment : Fragment(R.layout.fragment_task_list), TaskItemClickListener {

    private val binding by viewBinding(FragmentTaskListBinding::bind)
    private val viewModel: TaskListViewModel by viewModels()
    private val adapter: TaskAdapter = TaskAdapter(this)

    companion object {

        @JvmStatic
        fun newInstance(): Fragment {
            return TaskListFragment()
        }
    }

    private val uiStateMapper: UiStateMapper = UiStateMapper()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        collectState()
    }

    private fun initViews() {
        binding.recycler.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        binding.recycler.adapter = adapter
        binding.addItem.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.commonContainer, TaskDetailsFragment.createFromScratch())
                addToBackStack("details")
            }
        }
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    val uiState = uiStateMapper.map(state)
                    adapter.submitList(uiState.items)
                }
            }
        }
    }

    override fun onCheckboxClicked(item: TaskUi) {
        viewModel.itemCheckStateChanged(item)
    }

    override fun onItemClicked(item: TaskUi) {
        parentFragmentManager.commit {
            replace(R.id.commonContainer, TaskDetailsFragment.edit(item.task.taskId))
            addToBackStack("details")
        }
    }
}
