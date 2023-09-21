package com.example.pruebafenix.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pruebafenix.ui.calendarpage.CalendarScreen
import com.example.pruebafenix.ui.calendarpage.CalendarViewModel
import com.example.pruebafenix.ui.newlesson.NewLessonScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel = CalendarViewModel()
    NavHost(navController = navController,
        startDestination = AppScreens.CalendarScreen.route,
        builder = {
            composable(route = AppScreens.CalendarScreen.route) { CalendarScreen(viewModel, navController)}
            composable(route = AppScreens.NewLessonScreen.route) { NewLessonScreen(navController) }
        })

}