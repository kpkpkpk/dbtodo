package com.kponomarev.databasetodo.ui.feature_list_task.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kponomarev.databasetodo.R
import com.kponomarev.databasetodo.ui.feature_list_task.presentation.model.TaskUi
import com.kponomarev.databasetodo.ui.feature_list_task.ui.TaskItemClickListener


class TaskAdapter(
    private val taskItemClickListener: TaskItemClickListener
) : ListAdapter<TaskUi, TaskViewHolder>(TaskItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view, taskItemClickListener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
