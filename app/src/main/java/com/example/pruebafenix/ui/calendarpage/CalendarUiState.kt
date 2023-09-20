package com.example.pruebafenix.ui.calendarpage

import com.example.pruebafenix.domain.model.LessonModel

data class CalendarUiState (
    val dayName: String = "",
    val dayNumber: Int = 0,
    val monthName: String = "",
    val currentLessonsList: List<LessonModel> = emptyList(),
    val currentMorningLessonsList: List<LessonModel> = emptyList(),
    val currentNoonLessonsList: List<LessonModel> = emptyList()
)