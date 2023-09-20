package com.example.pruebafenix.domain.model

data class LessonModel(
    val lessonColor: Int,
    val lessonDay: Int,
    val lessonName: String,
    val lessonStartTime: String,
    val lessonEndTime: String,
    val lessonVacancy: Int
)