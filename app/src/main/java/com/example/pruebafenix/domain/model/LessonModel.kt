package com.example.pruebafenix.domain.model

data class LessonModel(
    val lessonColor: Int,
    val lessonDay: String,
    val lessonName: String,
    val lessonStartTime: String,
    val lessonEndTime: String,
    val lessonVacancy: Int,
    val id: Long
)