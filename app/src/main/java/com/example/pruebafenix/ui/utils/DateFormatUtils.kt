package com.example.pruebafenix.ui.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

object DateFormatUtils {

    fun getDateFromLocalDate(localDate: LocalDate?): Date {
        var date: Date = SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())
        localDate?.let {
            date = SimpleDateFormat("yyyy-MM-dd").parse(it.toString())
        }
        return date
    }
    fun getDayName(date: Date): String {
        return SimpleDateFormat("EEEE", Locale ( "es" , "ES" ))
            .format(date).uppercase()
    }

    fun getDayNumber(date: Date): Int {
        return SimpleDateFormat("dd", Locale ( "es" , "ES" ))
            .format(date).toInt()
    }

    fun getMonthName(date: Date): String {
        return SimpleDateFormat("MMMM", Locale ( "es" , "ES"))
            .format(date)
    }
}