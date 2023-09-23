package com.example.pruebafenix.ui.newlesson

import com.example.pruebafenix.R

data class LessonInfoUiState(
    val lessonColor: Int = R.color.salmon,
    val lessonDay: String = "",
    val lessonName: String = "",
    val lessonStartTime: String = "",
    val lessonEndTime: String = "",
    val lessonVacancy: Int = 10,
    val id: Int = 0,
    var dropdownDayNameList: List<String> = emptyList(),
    val expanded: Boolean = false
)