package com.kponomarev.databasetodo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int = 0,
    val taskName: String = ""
) : Serializable
