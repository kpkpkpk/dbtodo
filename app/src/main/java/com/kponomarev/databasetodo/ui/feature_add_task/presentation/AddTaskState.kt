package com.kponomarev.databasetodo.ui.feature_add_task.presentation

import com.kponomarev.databasetodo.domain.model.Task

data class AddTaskState(
    val task: Task = Task()
)
