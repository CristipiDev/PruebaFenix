package com.example.pruebafenix.ui.calendarpage

import java.time.LocalDate

interface CalendarEvent {
    class OnClickNewDate(val onClickDate: LocalDate): CalendarEvent
}