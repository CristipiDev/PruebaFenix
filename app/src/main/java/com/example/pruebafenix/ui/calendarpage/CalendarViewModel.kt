package com.example.pruebafenix.ui.calendarpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CalendarViewModel: ViewModel() {

    var state by mutableStateOf(CalendarUiState())

    init {
       getDate(null)
    }

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

        state = state.copy(
            dayName = dayName,
            dayNumber = dayNumber,
            monthName = monthName
        )
    }

    fun onEvent(onEvent: CalendarEvent) {
        when(onEvent) {
            is CalendarEvent.OnClickNewDate -> {
                getDate(onEvent.onClickDate)
            }
        }
    }



}