package com.example.pruebafenix.ui.calendarpage

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pruebafenix.domain.model.LessonModel
import com.example.pruebafenix.domain.model.LessonsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CalendarViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(CalendarUiState())
    private val currentLessons = LessonsProvider()

    init {
       getDate(LocalDate.now())
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(localDate: LocalDate) {
        val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString())

        val dayName: String = SimpleDateFormat("EEEE", Locale ( "es" , "ES" ))
            .format(date).uppercase()

        val dayNumber = SimpleDateFormat("dd", Locale ( "es" , "ES" ))
            .format(date).toInt()

        val monthName = SimpleDateFormat("MMMM", Locale ( "es" , "ES"))
            .format(date)

        val listCurrentLessons = currentLessons.getLessonsForDay(dayName)

        val morningNoonLessonList = getMorningNoonLessonList(listCurrentLessons)

        val morningLessonList = morningNoonLessonList[0]
        val noonLessonList = morningNoonLessonList[1]


        state = state.copy(
            selectedDate = localDate,
            dayName = dayName,
            dayNumber = dayNumber,
            monthName = monthName,
            currentMorningLessonsList = morningLessonList,
            currentNoonLessonsList = noonLessonList
        )
    }

    fun onEvent(onEvent: CalendarEvent) {
        when(onEvent) {
            is CalendarEvent.OnClickNewDate -> {
                getDate(onEvent.onClickDate)
            }
        }
    }

    private fun getMorningNoonLessonList(listCurrentLessons: List<LessonModel>): List<List<LessonModel>> {
        val morningNoonLessonList: ArrayList<ArrayList<LessonModel>> = ArrayList()

        val morningLessonList: ArrayList<LessonModel> = ArrayList()
        val noonLessonList: ArrayList<LessonModel> = ArrayList()

        listCurrentLessons.forEach { lesson ->
            if(lesson.lessonStartTime.split(":")[0].toInt() <= 14){
                morningLessonList.add(lesson)
            } else {
                noonLessonList.add(lesson)
            }
        }

        morningNoonLessonList.add(morningLessonList)
        morningNoonLessonList.add(noonLessonList)

        return morningNoonLessonList
    }


}