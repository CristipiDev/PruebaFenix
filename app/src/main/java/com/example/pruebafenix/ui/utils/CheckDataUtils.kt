package com.example.pruebafenix.ui.utils

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.res.stringResource
import androidx.core.content.res.TypedArrayUtils.getString
import com.example.pruebafenix.R
import com.example.pruebafenix.domain.model.LessonModel
import com.example.pruebafenix.ui.newlesson.LessonInfoUiState

object CheckDataUtils {
    fun isEmptyDataState(state: LessonInfoUiState, context:Context): String {
        if (state.lessonName.isNullOrEmpty()) return context.getString(R.string.empty_name)
        if (state.lessonStartHourTime.isNullOrEmpty()) return context.getString(R.string.empty_start_hour)
        if (state.lessonStartMinTime.isNullOrEmpty()) return context.getString(R.string.empty_start_min)
        if (state.lessonEndHourTime.isNullOrEmpty()) return context.getString(R.string.empty_end_hour)
        if (state.lessonEndMinTime.isNullOrEmpty()) return context.getString(R.string.empty_end_min)

        return ""
    }

    fun checkStartHourEndHour(state: LessonInfoUiState, context:Context): String {
        //Comprobamos que las horas son números
        if (state.lessonStartHourTime.toIntOrNull() == null) return context.getString(R.string.check_start_hour_int)
        if (state.lessonStartMinTime.toIntOrNull() == null) return context.getString(R.string.check_start_min_int)
        if (state.lessonEndHourTime.toIntOrNull() == null) return context.getString(R.string.check_end_hour_int)
        if (state.lessonEndMinTime.toIntOrNull() == null) return context.getString(R.string.check_end_min_int)

        //Comprobamos que las horas son entre 0 y 24
        if (state.lessonStartHourTime.toInt() < 0 || state.lessonStartHourTime.toInt() > 24) return context.getString(R.string.check_start_hour_range)
        if (state.lessonEndHourTime.toInt() < 0 || state.lessonEndHourTime.toInt() > 24) return context.getString(R.string.check_end_hour_range)

        //Comprobamos que los minutos están entre 0 y 59
        if (state.lessonStartMinTime.toInt() < 0 || state.lessonStartMinTime.toInt() > 59) return context.getString(R.string.check_start_min_range)
        if (state.lessonEndMinTime.toInt() < 0 || state.lessonEndMinTime.toInt() > 59) return context.getString(R.string.check_end_min_range)

        //Comprobamos hora inicio vs. hora fin
        if(state.lessonStartHourTime > state.lessonEndHourTime) return context.getString(R.string.check_start_bigger_than_end)
        if(state.lessonStartHourTime == state.lessonEndHourTime &&
            state.lessonStartMinTime > state.lessonEndMinTime)  return context.getString(R.string.check_start_bigger_than_end)
        if (state.lessonStartHourTime == state.lessonEndHourTime &&
            state.lessonStartMinTime == state.lessonEndMinTime) return context.getString(R.string.check_start_equal_than_end)

        return ""
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

   fun splitTime(time: String): List<String> {
        return time.split(":")
    }
}