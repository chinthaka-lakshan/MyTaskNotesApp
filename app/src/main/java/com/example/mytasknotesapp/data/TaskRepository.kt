package com.example.mytasknotesapp.data

import androidx.lifecycle.LiveData

class TaskRepository (private val taskDao: TaskDao) {

    // Get All Tasks
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    // Insert New Task
    suspend fun insert(task: Task) {
        taskDao.Insert(task)
    }

    // Update Task
    suspend fun update(task: Task) {
        taskDao.Update(task)
    }

    // Delete Task
    suspend fun delete(task: Task) {
        taskDao.Delete(task)
    }

    // Get Task By Id
    suspend fun getTaskById(taskId: Int): Task? {
        return taskDao.getTaskById(taskId)
    }

}