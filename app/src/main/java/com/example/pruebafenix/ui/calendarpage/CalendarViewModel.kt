package com.example.pruebafenix.ui.calendarpage

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pruebafenix.domain.model.LessonModel
import com.example.pruebafenix.domain.model.LessonsProvider
import com.example.pruebafenix.ui.utils.DayNameEnum
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CalendarViewModel: ViewModel() {

    var state by mutableStateOf(CalendarUiState())
    val currentLessons = LessonsProvider()

    init {
       getDate(null)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(localDate: LocalDate?) {
        var date: Date? = null
        localDate?.let {
            date = SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString())
        }

        val dayName: String = SimpleDateFormat("EEEE", Locale ( "es" , "ES" ))
            .format(date ?: Calendar.getInstance().time).uppercase()

        val dayNumber = SimpleDateFormat("dd", Locale ( "es" , "ES" ))
            .format(date ?: Calendar.getInstance().time).toInt()

        val monthName = SimpleDateFormat("MMMM", Locale ( "es" , "ES"))
            .format(date ?: Calendar.getInstance().time)

        val listCurrentLessons = currentLessons.getLessonsForDay(getDayNameNumber(dayName))

        val morningLessonList = getMorningNoonLessonList(listCurrentLessons)[0]
        val noonLessonList = getMorningNoonLessonList(listCurrentLessons)[1]


        state = state.copy(
            dayName = dayName,
            dayNumber = dayNumber,
            monthName = monthName,
            currentLessonsList = listCurrentLessons,
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

    private fun getDayNameNumber(dayName: String): Int {
        return when(dayName) {
            "LUNES" -> { DayNameEnum.MONDAY.dayNumber }
            "MARTES" -> { DayNameEnum.TUESDAY.dayNumber }
            "MIÉRCOLES" -> { DayNameEnum.WEDNESDAY.dayNumber }
            "JUEVES" -> { DayNameEnum.THURSDAY.dayNumber }
            "VIERNES" -> { DayNameEnum.FRIDAY.dayNumber }
            "SÁBADO" -> { DayNameEnum.SATURDAY.dayNumber }
            else -> { DayNameEnum.SUNDAY.dayNumber }
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