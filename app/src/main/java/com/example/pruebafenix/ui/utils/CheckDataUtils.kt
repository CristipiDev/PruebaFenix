package com.example.pruebafenix.ui.utils

import com.example.pruebafenix.domain.model.LessonModel
import com.example.pruebafenix.ui.newlesson.LessonInfoUiState

object CheckDataUtils {
    fun isEmptyDataState(state: LessonInfoUiState): Boolean {
        if (state.lessonName.isNullOrEmpty()) return true
        if (state.lessonStartHourTime.isNullOrEmpty()) return true
        if (state.lessonStartMinTime.isNullOrEmpty()) return true
        if (state.lessonEndHourTime.isNullOrEmpty()) return true
        if (state.lessonEndMinTime.isNullOrEmpty()) return true
        if (state.lessonStartTime.isNullOrEmpty()) return true
        if (state.lessonEndTime.isNullOrEmpty()) return true

        return false
    }

    fun fromLessonIuStateToLessonModel(lessonState: LessonInfoUiState): LessonModel {
        return LessonModel(
            lessonState.lessonColor,
            lessonState.lessonDay,
            lessonState.lessonName,
            lessonState.lessonStartTime,
            lessonState.lessonEndTime,
            lessonState.lessonVacancy,
            lessonState.id
        )
    }
}