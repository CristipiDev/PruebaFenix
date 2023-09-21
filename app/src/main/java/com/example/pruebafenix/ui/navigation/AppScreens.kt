package com.example.pruebafenix.ui.navigation

sealed class AppScreens(val route: String) {
    object CalendarScreen: AppScreens(route = "calendar_screen")
    object NewLessonScreen: AppScreens(route = "new_lesson_screen")
}
