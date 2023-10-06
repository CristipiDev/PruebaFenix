package com.example.pruebafenix.ui.calendarpage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pruebafenix.ui.navigation.AppScreens
import com.example.pruebafenix.ui.theme.PruebaFenixTheme
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(true) {
        viewModel.updateStateFromLocalDate(viewModel.state.selectedDate)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fenix")},
                actions = {
                    IconButton(onClick = {
                        navController.navigate(route = AppScreens.LessonInfoScreen.route)
                    }) {
                        Icon(Icons.Filled.Add,
                            "Add")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
        ) {
            item {
                DayMenu(
                    viewModel.state,
                    viewModel::updateStateFromLocalDate)

                Divider(
                    color = MaterialTheme.colorScheme.background,
                    thickness = 5.dp
                )
                Text(text = "MAÑANAS",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(5.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }

            items(viewModel.state.currentMorningLessonsList) {lesson ->
                LessonBox(lesson.lessonColor,
                    lesson.lessonName,
                    lesson.lessonStartTime,
                    lesson.lessonEndTime,
                    lesson.lessonVacancy,
                    lesson.id,
                    navController)
            }


            item {
                Divider(
                    color = MaterialTheme.colorScheme.background,
                    thickness = 5.dp
                )
                Text(text = "TARDES",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(5.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )

            }

            items(viewModel.state.currentNoonLessonsList) {lesson ->
                LessonBox(lesson.lessonColor,
                    lesson.lessonName,
                    lesson.lessonStartTime,
                    lesson.lessonEndTime,
                    lesson.lessonVacancy,
                    lesson.id,
                    navController)
            }
        }


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayMenu(
    state: CalendarUiState,
    onNewDate: (LocalDate) -> Unit
) {
    val calendarState = rememberUseCaseState()

    CalendarDialog(state = calendarState,
        config = CalendarConfig(
            yearSelection = false,
            monthSelection = false,
            style = CalendarStyle.MONTH,
        ),
        selection = CalendarSelection.Date(
            selectedDate = state.selectedDate
        ) { newDate ->
            onNewDate(newDate)
        })

    Divider(
        color = MaterialTheme.colorScheme.background,
        thickness = 1.dp
    )
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {onNewDate(state.selectedDate!!.minusDays(1)) },
            modifier = Modifier.weight(1f)) {
            Icon(Icons.Filled.KeyboardArrowLeft,
                "Previous day",
                tint = MaterialTheme.colorScheme.onSecondary)
        }
        Text(
            text = "${state.dayName}, ${state.dayNumber} ${state.monthName}",
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(4f)
                .clickable { calendarState.show() }
        )

        IconButton(
            onClick = {onNewDate(state.selectedDate!!.plusDays(1)) },
            modifier = Modifier.weight(1f)){
            Icon(Icons.Filled.KeyboardArrowRight,
                "Next day",
                tint = MaterialTheme.colorScheme.onSecondary)
        }
    }
}

@Composable
fun LessonBox(
    colorContainer: Int,
    lessonName: String,
    startTime: String,
    endTime: String,
    availablePlaces: Int,
    id: Long,
    navController: NavController
) {
    Divider(
        color = MaterialTheme.colorScheme.background,
        thickness = 5.dp)
    Column(
        modifier = Modifier
            .background(color = colorResource(colorContainer))
            .fillMaxWidth()
            .clickable { }
            .padding(10.dp)
            .clickable { navController.navigate(
                route = AppScreens.LessonInfoScreen.route + "?lessonId=$id") }
    ){
        Text(
            text = lessonName,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "$startTime - $endTime",
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Plazas: $availablePlaces",
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //val viewModel = CalendarViewModel()
    val nav = NavController(LocalContext.current)
    PruebaFenixTheme {
       // CalendarScreen(viewModel, nav)
    }
}