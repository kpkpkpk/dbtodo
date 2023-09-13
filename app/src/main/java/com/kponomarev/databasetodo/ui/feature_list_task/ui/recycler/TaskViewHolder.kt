package com.kponomarev.databasetodo.ui.feature_list_task.ui.recycler

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kponomarev.databasetodo.R
import com.kponomarev.databasetodo.databinding.TaskItemBinding
import com.kponomarev.databasetodo.ui.feature_list_task.presentation.model.TaskUi
import com.kponomarev.databasetodo.ui.feature_list_task.ui.TaskItemClickListener

class TaskViewHolder(
    view: View,
    private val clickListener: TaskItemClickListener
) : RecyclerView.ViewHolder(view) {

    private val binding by viewBinding(TaskItemBinding::bind)

    fun bind(item: TaskUi) {
        with(binding) {
            taskTitle.text = item.task.taskName
            checkboxActivated(item)
            checkbox.isChecked = item.isChecked
            checkbox.setOnClickListener {
                clickListener.onCheckboxClicked(item)
            }
            commonContainer.setOnClickListener {
                clickListener.onItemClicked(item)
            }
        }
    }

    private fun checkboxActivated(item: TaskUi) {
        with(binding) {
            if (item.isChecked) {
                taskTitle.setTextColor(ContextCompat.getColor(root.context, R.color.gray))
                taskTitle.paintFlags = taskTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
            } else {
                taskTitle.setTextColor(ContextCompat.getColor(root.context, R.color.black))
                taskTitle.paintFlags = taskTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            }
            checkbox.isChecked = item.isChecked
        }
    }

}
