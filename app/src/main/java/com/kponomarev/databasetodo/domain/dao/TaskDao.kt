package com.kponomarev.databasetodo.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.kponomarev.databasetodo.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * from Task")
    fun observeTasks(): Flow<List<Task>>

    @Query("SELECT * from Task")
    suspend fun getAllTasks(): List<Task>

    @Query("SELECT * from Task where taskId=:taskId")
    suspend fun getTaskById(taskId: Int): Task

    @Delete
    suspend fun deleteTask(task: Task)

    @Insert(onConflict = REPLACE)
    suspend fun insertTask(task: Task)

}
