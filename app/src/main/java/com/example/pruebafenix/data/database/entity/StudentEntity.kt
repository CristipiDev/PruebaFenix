package com.example.pruebafenix.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class StudentEntity(
    @ColumnInfo(name = "studentName") val studentName: String,
    @PrimaryKey(autoGenerate = true) var studentId: Long = 0
)