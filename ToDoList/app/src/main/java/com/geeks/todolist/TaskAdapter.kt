package com.geeks.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val taskList: List<Task>,
    private val onItemClick: (Task) -> Unit,
    private val onItemLongClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            val taskCheckbox = itemView.findViewById<CheckBox>(R.id.checkbox)

            itemView.setOnClickListener { onItemClick(taskList[adapterPosition]) }
            itemView.setOnLongClickListener {
                onItemLongClick(taskList[adapterPosition])
                true
            }

            taskCheckbox.setOnCheckedChangeListener { _, isChecked ->
                val task = taskList[adapterPosition]
                task.isCompleted = isChecked
            }
        }

        fun bind(task: Task) {
            val taskTitleTextView = itemView.findViewById<TextView>(R.id.title)
            val taskCheckbox = itemView.findViewById<CheckBox>(R.id.checkbox)

            taskTitleTextView.text = task.title
            taskCheckbox.isChecked = task.isCompleted
        }
    }
}