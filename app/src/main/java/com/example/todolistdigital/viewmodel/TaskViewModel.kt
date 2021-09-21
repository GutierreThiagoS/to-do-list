package com.example.todolistdigital.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todolistdigital.model.Task
import com.example.todolistdigital.repository.TaskRepository

class TaskViewModel(private val repository: TaskRepository): ViewModel() {

    val taskList : LiveData<List<Task>> = repository.getTaskList()

    fun insertOrUpdateTask(task: Task){
        repository.insert(task)
    }

    fun deleteTask(task: Task){
        repository.delete(task)
    }

    fun getTask(taskId: Int): Task?{
        return repository.getTask(taskId)
    }
}