package com.kponomarev.databasetodo.ui.feature_list_task.presentation.model

import com.kponomarev.databasetodo.domain.model.Task

data class TaskUi(
    val task: Task,
    val isChecked: Boolean = false
)
