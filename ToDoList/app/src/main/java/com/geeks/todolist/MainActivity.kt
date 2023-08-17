package com.geeks.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add_bt = findViewById<Button>(R.id.add_bt)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val task_et = findViewById<EditText>(R.id.task_et)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        taskAdapter = TaskAdapter(viewModel.getAllTasks(),
            onItemClick = { task -> viewModel.markTaskCompleted(task) },
            onItemLongClick = { task -> showDeleteDialog(task) })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        add_bt.setOnClickListener {
            val taskTitle = task_et.text.toString()
            if (taskTitle.isNotBlank()) {
                viewModel.addTask(taskTitle)
                taskAdapter.notifyDataSetChanged()
                task_et.text.clear()
            }
        }
    }

    private fun showDeleteDialog(task: Task) {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.delete_task))
            .setMessage(getString(R.string.delete_this_task))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                viewModel.removeTask(task)
                taskAdapter.notifyDataSetChanged()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .create()

        alertDialog.show()
    }
}
