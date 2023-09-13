package com.kponomarev.databasetodo.ui.feature_list_task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kponomarev.databasetodo.domain.repository.TaskRepository
import com.kponomarev.databasetodo.ui.feature_list_task.presentation.model.TaskUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(TaskState())
    val state
        get() = _state.asStateFlow()

    init {
        observeItems()
    }

    private fun observeItems() {
        viewModelScope.launch {
            taskRepository.observe().collectLatest { items ->
                _state.value = _state.value.copy(items = items)
            }
        }
    }

    fun itemCheckStateChanged(item: TaskUi) {
        val newSelectedItems = _state.value.selectedItems.reduceId(item.task.taskId)
        _state.value = _state.value.copy(selectedItems = newSelectedItems)
        deleteCheckedItems()
    }

    @OptIn(FlowPreview::class)
    private fun deleteCheckedItems() {
        viewModelScope.launch {
            _state.debounce(2000).collectLatest { state ->
                val checkedItems = state.items.filter { state.selectedItems.contains(it.taskId) }
                taskRepository.deleteTasks(checkedItems)
            }
            _state.value = _state.value.copy(selectedItems = emptySet())
        }
    }

    private fun Set<Int>.reduceId(taskId: Int): Set<Int> {
        return if (contains(taskId)) minus(taskId) else plus(taskId)
    }
}
