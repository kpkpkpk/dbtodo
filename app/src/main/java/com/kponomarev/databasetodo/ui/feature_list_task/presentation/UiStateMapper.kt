package com.kponomarev.databasetodo.ui.feature_list_task.presentation

import com.kponomarev.databasetodo.domain.model.Task
import com.kponomarev.databasetodo.ui.feature_list_task.presentation.model.TaskUi

class UiStateMapper {
    fun map(state: TaskState): TaskUiState {
        return TaskUiState(items = state.items.map { mapTaskToUi(state.selectedItems, it) })
    }

    private fun mapTaskToUi(selectedItems: Set<Int>, task: Task): TaskUi {
        return TaskUi(
            task = task,
            isChecked = selectedItems.contains(task.taskId)
        )
    }
}
