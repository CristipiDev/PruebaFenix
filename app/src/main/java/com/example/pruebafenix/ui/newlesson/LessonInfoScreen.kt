package com.example.pruebafenix.ui.newlesson

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pruebafenix.R

@Composable
fun LessonInfoScreen(
    viewModel: LessonInfoViewModel = hiltViewModel(),
    navController: NavController
) {
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

        //Columna de nombre de día
        item {
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
        Text(text = stringResource(R.string.background_color_title),
            style = MaterialTheme.typography.headlineMedium)

        partsColorList.forEach {partList ->
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {

                partList.forEach {color ->
                    if (color == selectedColor) RadioButtonColor(color, true, onChangeColor)
                    else RadioButtonColor(color, false, onChangeColor)

                }
            }
        }
    }

}

@Composable
private fun RadioButtonColor(
    color: Int,
    isSelected: Boolean,
    onChangeColor: (Int) -> Unit
) {
    var border = BorderStroke(0.dp, colorResource(color))
    if (isSelected) border = BorderStroke(5.dp, MaterialTheme.colorScheme.primary)

    IconButton(onClick = { onChangeColor(color)}) {
        Card(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(size = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = color)),
            border = border

        ){}
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun NameRow(
    lessonName: String,
    onChangeName: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 15.dp))
    {
        Text(text = stringResource(R.string.lesson_name_title),
            style = MaterialTheme.typography.headlineMedium)
        BasicTextField(
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            value = lessonName,
            onValueChange = { onChangeName(it) },
            textStyle = MaterialTheme.typography.labelMedium,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()}),
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
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    var state = LessonInfoUiState()
    val dropdownDayNameList = listOf("LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO")

    state = state.copy(
        lessonColor = R.color.salmon,
        dropdownDayNameList = dropdownDayNameList,
        lessonDay = "LUNES",
        lessonColorList = listOf(R.color.salmon, R.color.blue, R.color.caramel,
            R.color.pink, R.color.lilac, R.color.orange, R.color.grey,
            R.color.purple, R.color.green)
    )

    MainBody(state, {}, {})
}

@Preview(showBackground = true)
@Composable
fun radioButtonColorPreview() {
    val list = listOf(R.color.salmon, R.color.blue, R.color.caramel,
        R.color.pink, R.color.lilac, R.color.orange, R.color.grey,
        R.color.purple, R.color.green)
    ColorsRow(R.color.salmon, list, {})
}

@Composable
private fun MainBody(
    state: LessonInfoUiState,
    changeExpandedDropdown: (Boolean) -> Unit,
    onClickItemDropdown:(String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.salmon))
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End)
        {
            IconButton(onClick = {  }) {
                Icon(
                    Icons.Filled.Close,
                    "Close",
                    tint = MaterialTheme.colorScheme.primary)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dia:",
                style = MaterialTheme.typography.headlineMedium)

            CustomDropdown(
                state.lessonDay,
                state.dropdownDayNameList,
                state.expanded,
                changeExpandedDropdown,
                onClickItemDropdown)
        }

        ColorsRow(
            state.lessonColor,
            state.lessonColorList,
            { })

        NameRow("Pole Sport", {})
    }
}