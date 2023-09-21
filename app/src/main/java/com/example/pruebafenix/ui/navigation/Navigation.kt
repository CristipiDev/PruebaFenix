package com.example.pruebafenix.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pruebafenix.ui.newlesson.NewLessonScreen
import com.example.pruebafenix.ui.calendarpage.CalendarScreen
import com.example.pruebafenix.ui.calendarpage.CalendarViewModel

@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel = CalendarViewModel()
    NavHost(
        navController = navController,
        startDestination = AppScreens.CalendarScreen.route, builder = {
            composable(AppScreens.CalendarScreen.route) {
                CalendarScreen(
                    viewModel = viewModel,
                    navController = navController
                )
            }
            composable(AppScreens.NewLessonScreen.route) {
                NewLessonScreen(navController= navController)
            }
        })
}