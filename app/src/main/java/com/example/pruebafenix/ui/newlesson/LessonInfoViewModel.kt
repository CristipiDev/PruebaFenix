package com.example.pruebafenix.ui.newlesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pruebafenix.domain.model.LessonModel
import com.example.pruebafenix.domain.usecase.SetNewLessonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LessonInfoViewModel @Inject constructor(
    private val setNewLessonUseCase: SetNewLessonUseCase
): ViewModel() {

    var state by mutableStateOf(LessonInfoUiState(
        dropdownDayNameList = listOf("LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO")
    ))

    suspend fun setNewLessonInDb(state: LessonInfoUiState){
        val newLesson = LessonModel(
            state.lessonColor,
            state.lessonDay,
            state.lessonName,
            state.lessonStartTime,
            state.lessonEndTime,
            state.lessonVacancy,
            state.id
        )

        setNewLessonUseCase.lessonToAdd(newLesson)
        setNewLessonUseCase.invoke()
    }

    fun onEvent(event: LessonInfoEvent) {
        when(event) {
            is LessonInfoEvent.OnChangeColor ->  state = state.copy(lessonColor = event.color)
            is LessonInfoEvent.OnChangeDayName -> state = state.copy(lessonDay = event.dayName)
            is LessonInfoEvent.OnChangeName -> state = state.copy(lessonName = event.name)
            is LessonInfoEvent.OnChangeStartTime -> state = state.copy(lessonStartTime = event.startTime)
            is LessonInfoEvent.OnChangeEndTime -> state = state.copy(lessonEndTime = event.endTime)
            is LessonInfoEvent.OnChangeVacancy -> state = state.copy(lessonVacancy = event.vacancy)
            else -> {}
        }
    }

}