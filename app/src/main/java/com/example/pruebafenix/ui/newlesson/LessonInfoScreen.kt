package com.example.pruebafenix.ui.newlesson

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pruebafenix.R
import com.example.pruebafenix.ui.utils.CheckDataUtils

@Composable
fun LessonInfoScreen(
    viewModel: LessonInfoViewModel = hiltViewModel(),
    navController: NavController,
    lessonId: Int? = null
) {

    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.getLessonFromId(lessonId)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = viewModel.state.lessonColor))
            .padding(10.dp)
    ) {
        //Columna con boton de cierre
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            )
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Filled.Close,
                        "Close",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
        }

        //Columna de plazas y nombre de día
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.day_title),
                        style = MaterialTheme.typography.headlineMedium
                    )

                    CustomDropdown(
                        viewModel.state.lessonDay,
                        viewModel.state.dropdownDayNameList,
                        viewModel.state.expanded,
                        viewModel::changeExpandedDropdown,
                        viewModel::onChangeDayName
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
        }

        //Columna de los colores
        item {
            ColorsRow(
                viewModel.state.lessonColor,
                viewModel.state.lessonColorList,
                viewModel::onChangeColor
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
        }

        //Columna de nombre de la clase
        item {
            NameRow(viewModel.state.lessonName, viewModel::onChangeName)

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

        }

        //Row hora inicio
        item {
            TimeRow(
                stringResource(R.string.start_time_title),
                viewModel.state.lessonStartHourTime,
                viewModel.state.lessonStartMinTime,
                viewModel::onChangeStartHourTime,
                viewModel::onChangeStartMinTime
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
        }

        //Row Hora fin
        item {
            TimeRow(
                stringResource(R.string.end_time_title),
                viewModel.state.lessonEndHourTime,
                viewModel.state.lessonEndMinTime,
                viewModel::onChangeEndHourTime,
                viewModel::onChangeEndMinTime
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
        }

        //Colum de Vacancy
        item {
            VacancyRow(
                viewModel.state.lessonVacancy,
                viewModel::onChangeVacancy)
            Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                    )
        }

        //Row de botones
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (viewModel.state.isUpdateDeleteLesson) {
                        CustomButton(
                            text = stringResource(R.string.delete_button),
                            onClickButton = {
                                viewModel.deleteLessonFromId(viewModel.state.id)
                                navController.popBackStack()},
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(size = 5.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    CustomButton(
                        text = stringResource(R.string.save_button),
                        {
                            if (!CheckDataUtils.isEmptyDataState(viewModel.state)) {
                                if (viewModel.state.isUpdateDeleteLesson) {
                                    viewModel.updateLesson(viewModel.state)
                                }else {
                                    viewModel.setNewLessonInDb()
                                    navController.popBackStack()
                                }
                            } else {
                                Toast.makeText(context,
                                "Debes tener todos los datos rellenados", Toast.LENGTH_LONG).show()}
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(size = 5.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

@Composable
private fun CustomDropdown(
    lessonDay: String,
    dayNameList: List<String>,
    expanded: Boolean,
    changeExpandedDropdown: (Boolean) -> Unit,
    onClickItemDropdown: (String) -> Unit
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
                    style = MaterialTheme.typography.labelMedium
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
private fun ColorsRow(
    selectedColor: Int,
    colorList: List<Int>,
    onChangeColor: (Int) -> Unit
) {
    val partsColorList = colorList.chunked(5)
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.background_color_title),
            style = MaterialTheme.typography.headlineMedium
        )

        partsColorList.forEach { partList ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                partList.forEach { color ->
                    if (color == selectedColor) CustomRadioButtonColor(color, true, onChangeColor)
                    else CustomRadioButtonColor(color, false, onChangeColor)

                }
            }
        }
    }

}

@Composable
private fun CustomRadioButtonColor(
    color: Int,
    isSelected: Boolean,
    onChangeColor: (Int) -> Unit
) {
    var border = BorderStroke(0.dp, colorResource(color))
    if (isSelected) border = BorderStroke(5.dp, MaterialTheme.colorScheme.primary)

    IconButton(onClick = { onChangeColor(color) }) {
        Card(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(size = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = color)
            ),
            border = border

        ) {}
    }
}

@Composable
private fun NameRow(
    lessonName: String,
    onChangeName: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    )
    {
        Text(
            text = stringResource(R.string.lesson_name_title),
            style = MaterialTheme.typography.headlineMedium
        )
        CustomBasicTextField(
            lessonName, onChangeName,
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            keyboardType = KeyboardType.Text
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CustomBasicTextField(
    lessonName: String,
    onChangeValue: (String) -> Unit,
    modifier: Modifier,
    keyboardType: KeyboardType
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    BasicTextField(
        maxLines = 1,
        modifier = modifier,
        value = lessonName,
        onValueChange = { onChangeValue(it) },
        textStyle = MaterialTheme.typography.labelMedium,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
        decorationBox = { innerTextField ->
            Row(modifier = Modifier
                .drawWithContent {
                    drawContent()
                    clipRect {
                        val strokeWidth = Stroke.DefaultMiter
                        val y = size.height
                        drawLine(
                            brush = SolidColor(Color(R.color.dark_brown)),
                            strokeWidth = strokeWidth,
                            cap = StrokeCap.Square,
                            start = Offset.Zero.copy(y = y),
                            end = Offset(x = size.width, y = y)
                        )
                    }
                }
            ) {
                innerTextField()
            }
        }
    )
}

@Composable
private fun TimeRow(
    title: String,
    startTime: String,
    endTime: String,
    onChangeHour: (String) -> Unit,
    onChangeMin: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            ) {
                Row {
                    CustomBasicTextField(
                        startTime, onChangeHour,
                        Modifier
                            .width(40.dp)
                            .padding(end = 5.dp),
                        keyboardType = KeyboardType.Number
                    )
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.labelMedium
                    )
                    CustomBasicTextField(
                        endTime, onChangeMin,
                        Modifier
                            .width(40.dp)
                            .padding(start = 5.dp),
                        keyboardType = KeyboardType.Number
                    )
                }

            }

        }

    }
}
@Composable
private fun VacancyRow(
    vacancy: Int,
    onClick: (Int) -> Unit
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.vacancy_title),
            style = MaterialTheme.typography.headlineMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .width(30.dp),
                onClick = { onClick(vacancy.dec()) }) {
                Icon(
                    ImageVector.vectorResource(R.drawable.minus_solid),
                    "Previous day",
                    tint = MaterialTheme.colorScheme.primary)
            }
            Text(text = vacancy.toString(),
                style = MaterialTheme.typography.labelMedium)
            IconButton(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(30.dp),
                onClick = { onClick(vacancy.inc()) }) {
                Icon(
                    ImageVector.vectorResource(R.drawable.add_solid),
                    "Previous day",
                    tint = MaterialTheme.colorScheme.primary)
            }

        }
    }
}

@Composable
private fun CustomButton(
    text: String,
    onClickButton: () -> Unit,
    modifier: Modifier,
    colorText: Color
) {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClickButton() }) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = colorText,
                text = text
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun radioButtonColorPreview() {
    val list = listOf(
        R.color.salmon, R.color.blue, R.color.caramel,
        R.color.pink, R.color.lilac, R.color.orange, R.color.grey,
        R.color.purple, R.color.green
    )
    ColorsRow(R.color.salmon, list, {})
}

