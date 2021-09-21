package com.example.todolistdigital.repository

import com.example.todolistdigital.dao.TaskDao
import com.example.todolistdigital.model.Task
import org.koin.core.KoinComponent

class TaskRepository(
    private val taskDao: TaskDao
): KoinComponent {

    fun insert(task: Task){
        if (taskDao.update(task) == 0) taskDao.insert(task)
    }

    fun delete(task: Task){
        taskDao.delete(task)
    }

    fun getTask(taskId: Int): Task?{
        return taskDao.getTask(taskId)
    }

    fun getTaskList() = taskDao.gelAll()
}