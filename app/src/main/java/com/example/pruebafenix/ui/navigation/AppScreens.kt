package com.example.pruebafenix.ui.navigation

sealed class AppScreens(val route: String) {
    object CalendarScreen: AppScreens(route = "calendar_screen")
    object LessonInfoScreen: AppScreens(route = "lesson_info_screen")
}
