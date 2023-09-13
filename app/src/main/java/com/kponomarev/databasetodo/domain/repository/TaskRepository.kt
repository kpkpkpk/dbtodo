package com.kponomarev.databasetodo.domain.repository

import com.kponomarev.databasetodo.domain.dao.TaskDao
import com.kponomarev.databasetodo.domain.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val dao: TaskDao
) {

    suspend fun observe(): Flow<List<Task>> {
        return dao.observeTasks()
    }

    suspend fun getAll(): List<Task> {
        return dao.getAllTasks()
    }

    suspend fun getTaskById(taskId: Int): Task {
        return dao.getTaskById(taskId)
    }

    suspend fun deleteTask(task: Task) {
        return dao.deleteTask(task)
    }

    suspend fun deleteTasks(tasks: List<Task>) {
        return tasks.forEach { dao.deleteTask(it) }
    }

    suspend fun insertTask(task: Task) {
        return dao.insertTask(task)
    }
}
