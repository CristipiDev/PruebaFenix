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
    private val currentLessons = LessonsProvider()

    var state by mutableStateOf(CalendarUiState(
        selectedDate = LocalDate.now(),
        dayName = getDayName(getDateFromLocalDate(LocalDate.now())),
        dayNumber = getDayNumber(getDateFromLocalDate(LocalDate.now())),
        monthName = getMonthName(getDateFromLocalDate(LocalDate.now())),
        currentMorningLessonsList = getMorningNoonLessonList(
            currentLessons.getLessonsForDay(getDayName(getDateFromLocalDate(LocalDate.now()))))[0],
        currentNoonLessonsList = getMorningNoonLessonList(
            currentLessons.getLessonsForDay(getDayName(getDateFromLocalDate(LocalDate.now()))))[1]
    ))


    @SuppressLint("SimpleDateFormat")
    fun getDate(localDate: LocalDate?) {

        val dayName: String = getDayName(getDateFromLocalDate(localDate))
        val dayNumber = getDayNumber(getDateFromLocalDate(localDate))
        val monthName = getMonthName(getDateFromLocalDate(localDate))

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

    private fun getDateFromLocalDate(localDate: LocalDate?): Date {
        var date: Date = SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())
        localDate?.let {
            date = SimpleDateFormat("yyyy-MM-dd").parse(it.toString())
        }
        return date
    }
    private fun getDayName(date: Date): String {
        return SimpleDateFormat("EEEE", Locale ( "es" , "ES" ))
            .format(date).uppercase()
    }

    private fun getDayNumber(date: Date): Int {
        return SimpleDateFormat("dd", Locale ( "es" , "ES" ))
            .format(date).toInt()
    }

    private fun getMonthName(date: Date): String {
        return SimpleDateFormat("MMMM", Locale ( "es" , "ES"))
            .format(date)
    }

}