package com.example.mytasknotesapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    // Insert New Task
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(task: Task)

    // Update Task
    @Update
    suspend fun Update(task: Task)

    // Delete Task
    @Delete
    suspend fun Delete(task: Task)

    // Get All Tasks
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAllTasks(): LiveData<List<Task>>

    // Get Task By Id
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Task?
}