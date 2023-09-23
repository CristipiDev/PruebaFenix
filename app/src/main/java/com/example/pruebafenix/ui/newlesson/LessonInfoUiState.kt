package com.example.pruebafenix.ui.newlesson

data class LessonInfoUiState(
    val lessonColor: Int = 0,
    val lessonDay: String = "",
    val lessonName: String = "",
    val lessonStartTime: String = "",
    val lessonEndTime: String = "",
    val lessonVacancy: Int = 10,
    val id: Int = 0,
    val dropdownDayNameList: List<String> = emptyList(),
    val expanded: Boolean = false,
    val lessonColorList: List<Int> = emptyList()
)