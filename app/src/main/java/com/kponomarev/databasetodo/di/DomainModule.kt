package com.kponomarev.databasetodo.di

import android.content.Context
import androidx.room.Room
import com.kponomarev.databasetodo.domain.TaskDatabase
import com.kponomarev.databasetodo.domain.dao.TaskDao
import com.kponomarev.databasetodo.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): TaskDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "task-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: TaskDatabase): TaskDao {
        return database.tasksDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: TaskDao): TaskRepository {
        return TaskRepository(dao)
    }
}
