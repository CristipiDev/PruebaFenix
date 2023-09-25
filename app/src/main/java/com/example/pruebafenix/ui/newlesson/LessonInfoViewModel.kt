package com.example.pruebafenix.ui.newlesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebafenix.R
import com.example.pruebafenix.domain.model.LessonModel
import com.example.pruebafenix.domain.usecase.SetNewLessonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonInfoViewModel @Inject constructor(
    private val setNewLessonUseCase: SetNewLessonUseCase
): ViewModel() {

    var state by mutableStateOf(LessonInfoUiState(
        lessonColor = R.color.salmon,
        lessonDay = "LUNES",
        dropdownDayNameList = listOf("LUNES", "MARTES", "MIÉRCOLES", "JUEVES",
            "VIERNES", "SABADO", "DOMINGO"),
        lessonColorList = listOf(R.color.salmon, R.color.blue, R.color.caramel,
               R.color.pink, R.color.lilac, R.color.orange, R.color.grey,
               R.color.purple, R.color.green)

    ))

    fun setNewLessonInDb(){
        viewModelScope.launch {
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
    }

    fun onEvent(event: LessonInfoEvent) {
        when(event) {
            is LessonInfoEvent.OnChangeColor ->  state = state.copy(lessonColor = event.color)
            is LessonInfoEvent.OnChangeDayName -> state = state.copy(lessonDay = event.dayName)
            is LessonInfoEvent.OnChangeName -> state = state.copy(lessonName = event.name)
            is LessonInfoEvent.OnChangeStartTime -> state = state.copy(lessonStartTime = event.startTime)
            is LessonInfoEvent.OnChangeEndTime -> state = state.copy(lessonEndTime = event.endTime)
            is LessonInfoEvent.OnChangeVacancy -> state = state.copy(lessonVacancy = event.vacancy)
            is LessonInfoEvent.OnClickDropdown -> state = state.copy(expanded = !event.expanded)
            else -> {}
        }
    }

    //Dropdown
    fun changeExpandedDropdown(expanded: Boolean) { state = state.copy(expanded = !expanded) }
    fun onChangeDayName(newDayName: String) {
        state = state.copy(
            lessonDay = newDayName,
            expanded = false
        )
    }

    //RadioButton color
    fun onChangeColor(newColor: Int) { state = state.copy(lessonColor = newColor) }

    //TextField: nombre de clase
    fun onChangeName(name: String) { state = state.copy(lessonName = name)}

    //TextField de hora de inicio
    fun onChangeStartHourTime(hour: String) {
        //TODO comprobaciones de las horas
        val min = state.lessonStartMinTime
        state = state.copy(
            lessonStartHourTime = hour,
            lessonStartTime = "$hour:$min"
        )
    }
    fun onChangeStartMinTime(min: String) {
        val hour = state.lessonStartHourTime
        state = state.copy(
            lessonStartMinTime = min,
            lessonStartTime = "$hour:$min"
        )}

    //TextField de hora de fin
    fun onChangeEndHourTime(hour: String) {
        //TODO comprobaciones de las horas
        val min = state.lessonEndMinTime
        state = state.copy(
            lessonEndHourTime = hour,
            lessonEndTime = "$hour:$min"
        )
    }
    fun onChangeEndMinTime(min: String) {
        val hour = state.lessonEndHourTime
        state = state.copy(
            lessonEndMinTime = min,
            lessonEndTime = "$hour:$min"
        )}

    //TextField de numero de plazas
    fun onChangeVacancy(vacancy: String) { state = state.copy(lessonVacancy = vacancy.toInt()) }

}