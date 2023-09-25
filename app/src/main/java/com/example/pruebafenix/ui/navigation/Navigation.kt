package com.example.pruebafenix.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.domain.usecase.SetNewLessonUseCase
import com.example.pruebafenix.ui.calendarpage.CalendarScreen
import com.example.pruebafenix.ui.calendarpage.CalendarViewModel
import com.example.pruebafenix.ui.newlesson.LessonInfoScreen
import com.example.pruebafenix.ui.newlesson.LessonInfoViewModel

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
            composable(AppScreens.LessonInfoScreen.route) {
                LessonInfoScreen(navController= navController)
            }
        })
}