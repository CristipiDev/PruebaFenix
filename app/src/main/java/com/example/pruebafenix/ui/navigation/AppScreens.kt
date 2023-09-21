package com.example.pruebafenix.ui.navigation

sealed class AppScreens(val route: String) {
    object CalendarScreen: AppScreens("calendar_screen")
    object NewLessonScreen: AppScreens("new_lesson_screen")
}
