package com.geeks.todolist

import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    val taskList = mutableListOf<Task>()

    fun addTask(title: String) {
        taskList.add(Task(title))
    }

    fun markTaskCompleted(task: Task) {
        task.isCompleted = true
    }

    fun removeTask(task: Task) {
        taskList.remove(task)
    }

    fun getAllTasks(): List<Task> {
        return taskList
    }
}