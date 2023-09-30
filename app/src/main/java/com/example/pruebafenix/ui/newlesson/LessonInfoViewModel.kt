package com.example.pruebafenix.ui.newlesson

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebafenix.R
import com.example.pruebafenix.domain.model.StudentModel
import com.example.pruebafenix.domain.usecase.DeleteLessonFromIdUseCase
import com.example.pruebafenix.domain.usecase.DeleteStudentFromIdUseCase
import com.example.pruebafenix.domain.usecase.GetLessonFromIdUseCase
import com.example.pruebafenix.domain.usecase.GetLessonWithStudentsFromIdUseCase
import com.example.pruebafenix.domain.usecase.SetNewLessonUseCase
import com.example.pruebafenix.domain.usecase.SetNewStudentIntoLessonFromIdUseCase
import com.example.pruebafenix.domain.usecase.SetNewStudentUseCase
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
    private val deleteLessonFromIdUseCase: DeleteLessonFromIdUseCase,
    private val updateLessonUseCase: UpdateLessonUseCase,
    private val getLessonWithStudentsFromIdUseCase: GetLessonWithStudentsFromIdUseCase,
    private val setNewStudentUseCase: SetNewStudentUseCase,
    private val setNewStudentIntoLessonFromIdUseCase: SetNewStudentIntoLessonFromIdUseCase,
    private val deleteStudentFromIdUseCase: DeleteStudentFromIdUseCase
): ViewModel() {

    var state by mutableStateOf(LessonInfoUiState(
        lessonColor = R.color.salmon,
        lessonDay = "LUNES",
        dropdownDayNameList = listOf("LUNES", "MARTES", "MIÉRCOLES", "JUEVES",
            "VIERNES", "SÁBADO", "DOMINGO"),
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

    fun getLessonFromId(lessonId: Long?) {
        lessonId?.let {
            viewModelScope.launch {
                getLessonWithStudentsFromIdUseCase.setLessonId(lessonId)
                val selectedLesson =  getLessonWithStudentsFromIdUseCase.invoke()

                state = state.copy(
                    lessonColor = selectedLesson.lesson.lessonColor,
                    lessonDay = selectedLesson.lesson.lessonDay,
                    lessonName = selectedLesson.lesson.lessonName,
                    lessonStartHourTime = splitTime(selectedLesson.lesson.lessonStartTime)[0],
                    lessonStartMinTime = splitTime(selectedLesson.lesson.lessonStartTime)[1],
                    lessonStartTime = selectedLesson.lesson.lessonStartTime,
                    lessonEndHourTime = splitTime(selectedLesson.lesson.lessonEndTime)[0],
                    lessonEndMinTime = splitTime(selectedLesson.lesson.lessonEndTime)[1],
                    lessonEndTime = selectedLesson.lesson.lessonEndTime,
                    lessonVacancy = selectedLesson.lesson.lessonVacancy,
                    id = selectedLesson.lesson.id,
                    isUpdateDeleteLesson = true,
                    studentList = selectedLesson.studentList
                )
            }
        }
    }

    fun deleteLessonFromId(lessonId: Long) {
        viewModelScope.launch {
            deleteLessonFromIdUseCase.setLessonId(lessonId)
            deleteLessonFromIdUseCase.invoke()
        }
    }

    fun setStudentIntoLessonFromId(studentName: String, lessonId: Long) {
        val student = StudentModel(studentName, 0)
        var studentId: Long = -1

        viewModelScope.launch {
            setNewStudentUseCase.setStudent(student)
            studentId = setNewStudentUseCase.invoke()

            if(studentId >= 0 ) {
                //TODO actualizar el numero de plazas disponibles

                //onChangeVacancy(state.lessonVacancy - 1)
                //updateLesson()

                onChangeDialogTextField("")

                setNewStudentIntoLessonFromIdUseCase.setLessonId(lessonId)
                setNewStudentIntoLessonFromIdUseCase.setStudentId(studentId)

                setNewStudentIntoLessonFromIdUseCase.invoke()

                getLessonFromId(lessonId)
            }
        }
    }

    fun deleteStudentFromId(studentId: Long, lessonId: Long) {
        viewModelScope.launch {
            deleteStudentFromIdUseCase.setStudentId(studentId)
            deleteStudentFromIdUseCase.invoke()

            getLessonFromId(lessonId)
        }
    }

    fun checkErrorData(context: Context): String {
        var errorString = CheckDataUtils.isEmptyDataState(state, context)
        if (errorString.isEmpty()) errorString = CheckDataUtils.checkStartHourEndHour(state, context)
        return errorString
    }

    //TODO sacar los eventos del viewModel
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
        if (hour.length < 3) {
            state = state.copy(
                lessonStartHourTime = hour
            )
        }
    }
    fun onChangeStartMinTime(min: String) {
        if (min.length < 3) {
            state = state.copy(
                lessonStartMinTime = min
            )
        }
    }

    //TextField de hora de fin
    fun onChangeEndHourTime(hour: String) {
        if (hour.length < 3) {
            state = state.copy(
                lessonEndHourTime = hour
            )
        }
    }
    fun onChangeEndMinTime(min: String) {
        if (min.length < 3) {
            state = state.copy(
                lessonEndMinTime = min
            )
        }
    }

    //TextField de numero de plazas
    fun onChangeVacancy(vacancy: Int) {
        state = state.copy(
            lessonVacancy = vacancy
        )
    }

    //Eventos del dialog
    fun setValueShowDialog(showDialog: Boolean) {
        state = state.copy(
            showDialog = showDialog
        )
    }
    fun onChangeDialogTextField(value: String){
        state = state.copy(
            studentName = value
        )
    }

}