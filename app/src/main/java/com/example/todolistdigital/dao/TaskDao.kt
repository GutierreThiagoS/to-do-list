package com.example.todolistdigital.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolistdigital.model.Task

@Dao
interface TaskDao {

    @Insert
    fun insert(vararg task: Task)

    @Update
    fun update(task: Task):Int

    @Query("SELECT * FROM Task")
    fun gelAll(): LiveData<List<Task>>

    @Query("""
        SELECT * FROM Task
        WHERE id = :taskId
        LIMIT 1
    """)
    fun getTask(taskId: Int): Task?

    @Delete
    fun delete(task: Task)
}