package com.example.mytasknotesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytasknotesapp.adapter.TaskAdapter
import com.example.mytasknotesapp.data.Task
import com.example.mytasknotesapp.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewTasks)
        fabAddTask = findViewById(R.id.fabAddTask)

        // Setup RecyclerView
        setupRecyclerView()

        // Initialize ViewModel
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        // Observe tasks from database
        taskViewModel.allTasks.observe(this) { tasks ->
            tasks?.let {
                taskAdapter.updateTasks(it)
            }
        }

        // Add task button
        fabAddTask.setOnClickListener {
            val intent = Intent(this, AddEditTaskActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(
            tasks = emptyList(),
            onTaskClick = { task ->
                // Edit task
                val intent = Intent(this, AddEditTaskActivity::class.java).apply {
                    putExtra("TASK_ID", task.id)
                    putExtra("TASK_TITLE", task.title)
                    putExtra("TASK_DESCRIPTION", task.description)
                }
                startActivity(intent)
            },
            onTaskComplete = { task ->
                // Toggle completion
                taskViewModel.toggleTaskCompletion(task)
            },
            onTaskDelete = { task ->
                // Delete task
                showDeleteConfirmationDialog(task)
            }
        )

        recyclerView.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showDeleteConfirmationDialog(task: Task) {
        AlertDialog.Builder(this)
            .setTitle("Delete Task")
            .setMessage("Are you sure you want to delete this task?\n\n\"${task.title}\"")
            .setPositiveButton("Delete") { dialog, _ ->
                // User confirmed deletion
                taskViewModel.delete(task)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // User cancelled
                dialog.dismiss()
            }
            .setCancelable(true)
            .show()
    }
}