package com.example.pruebafenix.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["id", "studentId"],
    foreignKeys = [
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class LessonStudentCrossRefEntity(
    val id: Long,
    val studentId: Long
)

