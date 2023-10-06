package com.example.pruebafenix.domain.model

data class LessonWithStudentsModel(
    val lesson: LessonModel,
    val studentList: List<StudentModel>
)