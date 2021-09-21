package com.example.todolistdigital.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val date: String,
    @ColumnInfo val hour: String,
)
