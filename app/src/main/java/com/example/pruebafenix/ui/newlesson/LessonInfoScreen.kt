package com.example.pruebafenix.ui.newlesson

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pruebafenix.R

@Composable
fun LessonInfoScreen(
    viewModel: LessonInfoViewModel = hiltViewModel(),
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = viewModel.state.lessonColor))
            .padding(10.dp)
    ) {
        //columna con boton de cierre
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

        //Columna de nombre de día
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(R.string.dayTitle),
                style = MaterialTheme.typography.headlineMedium)

            CustomDropdown(
                viewModel.state.lessonDay,
                viewModel.state.dropdownDayNameList,
                viewModel.state.expanded,
                viewModel::changeExpandedDropdown,
                viewModel::onClickItemDropdown)
        }
        ColorsRow(
            viewModel.state.lessonColor,
            viewModel.state.lessonColorList,
            viewModel::onChangeColor)

        NameRow("Nombre clase:")
        NameRow("Hora de inicio:")
        NameRow("Hora fin:")
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
        Text(text = "Color de fondo:",
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

@Composable
private fun NameRow(
    text: String
) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 15.dp))
    {
        Text(text = text,
            style = MaterialTheme.typography.headlineMedium)
        BasicTextField(
            value = "value",
            onValueChange = { },
            textStyle = MaterialTheme.typography.labelMedium,
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

        NameRow("Nombre clase:")
        NameRow("Hora de inicio:")
        NameRow("Hora fin:")
    }
}