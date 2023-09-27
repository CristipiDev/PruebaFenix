package com.example.pruebafenix.ui.newlesson

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebafenix.R
import com.example.pruebafenix.domain.usecase.DeleteLessonFromIdUseCase
import com.example.pruebafenix.domain.usecase.GetLessonFromIdUseCase
import com.example.pruebafenix.domain.usecase.SetNewLessonUseCase
import com.example.pruebafenix.domain.usecase.UpdateLessonUseCase
import com.example.pruebafenix.ui.utils.CheckDataUtils
import com.example.pruebafenix.ui.utils.CheckDataUtils.fromLessonIuStateToLessonModel
import com.example.pruebafenix.ui.utils.CheckDataUtils.splitTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonInfoViewModel @Inject constructor(
    private val setNewLessonUseCase: SetNewLessonUseCase,
    private val getLessonFromIdUseCase: GetLessonFromIdUseCase,
    private val deleteLessonFromIdUseCase: DeleteLessonFromIdUseCase,
    private val updateLessonUseCase: UpdateLessonUseCase
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
            state = state.copy(
                lessonStartTime = "${state.lessonStartHourTime}:${state.lessonStartMinTime}",
                lessonEndTime = "${state.lessonEndHourTime}:${state.lessonEndMinTime}"
            )
                val newLesson = fromLessonIuStateToLessonModel(state)
                setNewLessonUseCase.lessonToAdd(newLesson)
                setNewLessonUseCase.invoke()
        }

    }

    fun getLessonFromId(lessonId: Int?) {
        lessonId?.let {
            viewModelScope.launch {
                getLessonFromIdUseCase.setLessonId(lessonId)
                val selectedLesson =  getLessonFromIdUseCase.invoke()

                state = state.copy(
                    lessonColor = selectedLesson.lessonColor,
                    lessonDay = selectedLesson.lessonDay,
                    lessonName = selectedLesson.lessonName,
                    lessonStartHourTime = splitTime(selectedLesson.lessonStartTime)[0],
                    lessonStartMinTime = splitTime(selectedLesson.lessonStartTime)[1],
                    lessonStartTime = selectedLesson.lessonStartTime,
                    lessonEndHourTime = splitTime(selectedLesson.lessonEndTime)[0],
                    lessonEndMinTime = splitTime(selectedLesson.lessonEndTime)[1],
                    lessonEndTime = selectedLesson.lessonEndTime,
                    lessonVacancy = selectedLesson.lessonVacancy,
                    id = selectedLesson.id,
                    isUpdateDeleteLesson = true
                )
            }
        }
    }

    fun deleteLessonFromId(lessonId: Int) {
        viewModelScope.launch {
            deleteLessonFromIdUseCase.setLessonId(lessonId)
            deleteLessonFromIdUseCase.invoke()
        }
    }

    fun updateLesson() {
        viewModelScope.launch {
            state = state.copy(
                lessonStartTime = "${state.lessonStartHourTime}:${state.lessonStartMinTime}",
                lessonEndTime = "${state.lessonEndHourTime}:${state.lessonEndMinTime}"
            )
            val lessonModel = fromLessonIuStateToLessonModel(state)
            updateLessonUseCase.setLesson(lessonModel)
            updateLessonUseCase.invoke()
        }
    }

    fun checkErrorData(context: Context): String {
        var errorString = CheckDataUtils.isEmptyDataState(state, context)
        if (errorString.isEmpty()) errorString = CheckDataUtils.checkStartHourEndHour(state, context)
        return errorString
    }




    //EVENTOS
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
        state = state.copy(
            lessonStartHourTime = hour
        )
    }
    fun onChangeStartMinTime(min: String) {
        state = state.copy(
            lessonStartMinTime = min
        )}

    //TextField de hora de fin
    fun onChangeEndHourTime(hour: String) {
        state = state.copy(
            lessonEndHourTime = hour
        )
    }
    fun onChangeEndMinTime(min: String) {
        state = state.copy(
            lessonEndMinTime = min
        )}

    //TextField de numero de plazas
    fun onChangeVacancy(vacancy: Int) {
        state = state.copy(
            lessonVacancy = vacancy
        )
    }


}