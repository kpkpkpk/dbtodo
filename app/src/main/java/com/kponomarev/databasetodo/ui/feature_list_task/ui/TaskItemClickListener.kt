package com.kponomarev.databasetodo.ui.feature_list_task.ui

import com.kponomarev.databasetodo.ui.feature_list_task.presentation.model.TaskUi

interface TaskItemClickListener {
    fun onItemClicked(item: TaskUi)
    fun onCheckboxClicked(item: TaskUi)
}
