package com.kponomarev.databasetodo.ui.feature_add_task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kponomarev.databasetodo.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AddTaskState())
    val state
        get() = _state.asStateFlow()

    fun prefillTask(taskId: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(task = repository.getTaskById(taskId))
        }
    }

    fun saveChanges() {
        viewModelScope.launch {
            val task = _state.value.task
            repository.insertTask(task)
        }
    }

    fun modelChanged(taskName: String) {
        val newTask = _state.value.task.copy(taskName = taskName)
        _state.value = _state.value.copy(task = newTask)
    }
}
