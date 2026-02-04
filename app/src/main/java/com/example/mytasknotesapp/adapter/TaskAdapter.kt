package com.example.mytasknotesapp.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytasknotesapp.R
import com.example.mytasknotesapp.data.Task

/**
 * RecyclerView Adapter for displaying tasks
 * Handles task item clicks, completion toggle, and deletion
 */
class TaskAdapter(
    private var tasks: List<Task>,
    private val onTaskClick: (Task) -> Unit,
    private val onTaskComplete: (Task) -> Unit,
    private val onTaskDelete: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    /**
     * ViewHolder for task items
     */
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkboxComplete: CheckBox = itemView.findViewById(R.id.checkboxComplete)
        val tvTaskTitle: TextView = itemView.findViewById(R.id.tvTaskTitle)
        val tvTaskDescription: TextView = itemView.findViewById(R.id.tvTaskDescription)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        // Set task data
        holder.tvTaskTitle.text = task.title
        holder.tvTaskDescription.text = task.description
        holder.checkboxComplete.isChecked = task.isCompleted

        // Apply strikethrough if completed
        if (task.isCompleted) {
            holder.tvTaskTitle.paintFlags = holder.tvTaskTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.tvTaskTitle.paintFlags = holder.tvTaskTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        // Handle checkbox toggle
        holder.checkboxComplete.setOnClickListener {
            onTaskComplete(task)
        }

        // Handle delete button
        holder.btnDelete.setOnClickListener {
            onTaskDelete(task)
        }

        // Handle item click (for editing)
        holder.itemView.setOnClickListener {
            onTaskClick(task)
        }
    }

    override fun getItemCount(): Int = tasks.size

    /**
     * Update task list and refresh UI
     */
    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}