package com.example.mytasknotesapp.viewmodel  // Make sure package is viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mytasknotesapp.data.Task
import com.example.mytasknotesapp.data.TaskDatabase
import com.example.mytasknotesapp.data.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks
    }

    fun insert(task: Task) = viewModelScope.launch {
        if (task.title.isNotBlank()) {
            repository.insert(task)
        }
    }

    fun update(task: Task) = viewModelScope.launch {
        if (task.title.isNotBlank()) {
            repository.update(task)
        }
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }

    fun toggleTaskCompletion(task: Task) = viewModelScope.launch {
        task.isCompleted = !task.isCompleted
        repository.update(task)
    }
}