package com.kponomarev.databasetodo.ui.feature_list_task.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import com.kponomarev.databasetodo.ui.feature_list_task.presentation.model.TaskUi

class TaskItemDiffCallback : DiffUtil.ItemCallback<TaskUi>() {
    override fun areItemsTheSame(oldItem: TaskUi, newItem: TaskUi): Boolean {
        return oldItem.task == newItem.task;
    }

    override fun areContentsTheSame(oldItem: TaskUi, newItem: TaskUi): Boolean {
        return oldItem == newItem
    }

}
