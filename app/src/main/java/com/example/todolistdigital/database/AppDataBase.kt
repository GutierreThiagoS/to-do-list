package com.example.todolistdigital.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistdigital.dao.TaskDao
import com.example.todolistdigital.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}