package com.example.pruebafenix.data.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id", "studentId"])
data class LessonStudentCrossRefEntity(
    val id: Long,
    val studentId: Long
)

