package com.example.mytasknotesapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mytasknotesapp.data.Task
import com.google.android.material.textfield.TextInputEditText
import androidx.lifecycle.ViewModelProvider
import com.example.mytasknotesapp.viewmodel.TaskViewModel

/**
 * Activity for adding new task or editing existing task
 * Preserves state during configuration changes
 */
class AddEditTaskActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var etTaskTitle: TextInputEditText
    private lateinit var etTaskDescription: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var tvLabel: TextView

    private var taskId: Int = -1
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_task)

        // Initialize ViewModel
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        // Initialize views
        etTaskTitle = findViewById(R.id.etTaskTitle)
        etTaskDescription = findViewById(R.id.etTaskDescription)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
        tvLabel = findViewById(R.id.tvLabel)

        // Check if editing existing task
        intent?.let {
            taskId = it.getIntExtra("TASK_ID", -1)
            if (taskId != -1) {
                isEditMode = true
                tvLabel.text = "Edit Task"
                etTaskTitle.setText(it.getStringExtra("TASK_TITLE"))
                etTaskDescription.setText(it.getStringExtra("TASK_DESCRIPTION"))
            }
        }

        // Save button click
        btnSave.setOnClickListener {
            saveTask()
        }

        // Cancel button click
        btnCancel.setOnClickListener {
            finish()
        }
    }

    /**
     * Save or update task
     * SECURE CODING: Input validation before saving
     */
    private fun saveTask() {
        val title = etTaskTitle.text.toString().trim()
        val description = etTaskDescription.text.toString().trim()

        // Input validation
        if (title.isEmpty()) {
            etTaskTitle.error = "Title cannot be empty"
            etTaskTitle.requestFocus()
            return
        }

        // SECURE CODING: Sanitize input - limit length to prevent excessive data
        if (title.length > 100) {
            Toast.makeText(this, "Title too long (max 100 characters)", Toast.LENGTH_SHORT).show()
            return
        }

        if (description.length > 500) {
            Toast.makeText(this, "Description too long (max 500 characters)", Toast.LENGTH_SHORT).show()
            return
        }

        if (isEditMode) {
            // Update existing task
            val updatedTask = Task(
                id = taskId,
                title = title,
                description = description
            )
            taskViewModel.update(updatedTask)
            Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show()
        } else {
            // Create new task
            val newTask = Task(
                title = title,
                description = description
            )
            taskViewModel.insert(newTask)
            Toast.makeText(this, "Task saved", Toast.LENGTH_SHORT).show()
        }

        finish()
    }

    /**
     * Handle configuration changes - state is preserved by ViewModel
     * Input text is preserved automatically by EditText
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // EditText automatically saves its state
    }
}