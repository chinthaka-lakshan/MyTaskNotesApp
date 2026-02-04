// In app/src/main/java/com/example/mytasknotesapp/data/Task.kt
package com.example.mytasknotesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis(),
    var isCompleted: Boolean = false
)
