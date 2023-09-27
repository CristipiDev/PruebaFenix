package com.example.pruebafenix.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class LessonWithStudentsEntity(
    @Embedded val lesson: LessonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "studentId",
        associateBy = Junction(LessonStudentCrossRefEntity::class)
    )
    val students: List<StudentEntity>
)