package com.example.pruebafenix.ui.newlesson

import com.example.pruebafenix.domain.model.StudentModel

data class LessonInfoUiState(
    val lessonColor: Int = 0,
    val lessonDay: String = "",
    val lessonName: String = "",
    val lessonStartHourTime: String = "",
    val lessonStartMinTime: String = "",
    val lessonStartTime: String = "",
    val lessonEndHourTime: String = "",
    val lessonEndMinTime: String = "",
    val lessonEndTime: String = "",
    val lessonVacancy: Int = 10,
    val id: Long = -1,
    val dropdownDayNameList: List<String> = emptyList(),
    val expanded: Boolean = false,
    val lessonColorList: List<Int> = emptyList(),
    val isUpdateDeleteLesson: Boolean = false,
    val errorString: String = "",

    val studentList: List<StudentModel> = emptyList(),
    val studentName: String = "",

    val showDialog: Boolean = false
)