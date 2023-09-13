package com.kponomarev.databasetodo.ui.feature_list_task.presentation

import com.kponomarev.databasetodo.domain.model.Task

data class TaskState(
    val items: List<Task> = emptyList(),
    val selectedItems: Set<Int> = emptySet()
)
