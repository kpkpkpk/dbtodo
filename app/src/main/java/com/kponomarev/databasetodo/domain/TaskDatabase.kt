package com.kponomarev.databasetodo.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kponomarev.databasetodo.domain.dao.TaskDao
import com.kponomarev.databasetodo.domain.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun tasksDao(): TaskDao
}
