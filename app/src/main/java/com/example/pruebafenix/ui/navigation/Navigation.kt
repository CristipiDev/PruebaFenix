package com.example.pruebafenix.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
    NavHost(
        navController = navController,
        startDestination = AppScreens.CalendarScreen.route, builder = {
            composable(AppScreens.CalendarScreen.route) {
                CalendarScreen(navController = navController)
            }
            composable(route = AppScreens.LessonInfoScreen.route + "?lessonId={id}",
                arguments = listOf(
                    navArgument("id" , {
                        type = NavType.LongType}))
            ) {backStackEntry ->
                val id: Long? = backStackEntry.arguments?.getLong("id")

                LessonInfoScreen(navController= navController,
                    lessonId = id)
            }
            composable(route = AppScreens.LessonInfoScreen.route) {
                LessonInfoScreen(navController= navController)
            }
        })
}