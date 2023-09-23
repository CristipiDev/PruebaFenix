package com.example.pruebafenix.ui.newlesson

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LessonInfoScreen(
    viewModel: LessonInfoViewModel = hiltViewModel(),
    navController: NavController
) {
    MainBody(
        state = viewModel.state,
        navController = navController,
        viewModel::changeExpandedDropdown,
        viewModel::onClickItemDropdown)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainBody(
    state: LessonInfoUiState,
    navController: NavController,
    changeExpandedDropdown: (Boolean) -> Unit,
    onClickItemDropdown:(String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End)
        {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Filled.Close,
                    "Close",
                    tint = MaterialTheme.colorScheme.primary)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dia:",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium)

            CustomDropdown(
                state.lessonDay,
                state.dropdownDayNameList,
                state.expanded,
                changeExpandedDropdown,
                onClickItemDropdown)
        }

        ItemLesson("Nombre clase:")
        ItemLesson("Hora de inicio:")
        ItemLesson("Hora fin:")
    }
}

@Composable
private fun CustomDropdown(
    lessonDay: String,
    dayNameList: List<String>,
    expanded: Boolean,
    changeExpandedDropdown: (Boolean) -> Unit,
    onClickItemDropdown:(String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {

        IconButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { changeExpandedDropdown(expanded) }) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = lessonDay,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Box(contentAlignment = Alignment.TopEnd) {
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { changeExpandedDropdown(true) }
                    ) {
                        dayNameList.forEach { day ->
                            DropdownMenuItem(
                                text = { Text(day) },
                                onClick = { onClickItemDropdown(day) }
                            )
                        }
                    }
                }
            }
        }



    }
}

@Composable
private fun ItemLesson(
    text: String
) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 15.dp))
    {
        Text(text = text,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary)
        BasicTextField(
            value = "value",
            onValueChange = { },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                ) {
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val nav = NavController(LocalContext.current)
    val state = LessonInfoUiState()
    val dropdownDayNameList = listOf("LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO")

    state.dropdownDayNameList = dropdownDayNameList

    MainBody(state, nav, {}, {})
}