package com.example.pruebafenix.ui.newlesson

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pruebafenix.R

@Composable
fun NewLessonScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.Close,
                    "Close")
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        ItemsLesson("Nombre de la clase:")
        ItemsLesson("Hora inicio:")
        ItemsLesson("Hora fin:")
        ItemsLesson("Numero de plazas:")

        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xffb9b9b9),
                    shape = RoundedCornerShape(percent = 10)
                )
                .padding(horizontal = 12.dp, vertical = 10.dp),
            leadingIcon = { Icon(Icons.Filled.Email, "Email") },
            trailingIcon = { Icon(Icons.Filled.Email, "Email") },
            paddingLeadingIconEnd = 10.dp,
            paddingTrailingIconStart = 10.dp
        )

        Row(
            Modifier
                .background(
                    colorResource(id = R.color.grey)
                )
                .padding(10.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp).background(Color.White, RoundedCornerShape(22.dp)),
                shape = RoundedCornerShape(22.dp),
                value = "",
                onValueChange = {},
                textStyle = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun ItemsLesson(
    titleName: String
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Text(text = titleName,
            modifier = Modifier.weight(1f))
        TextField(value = "", onValueChange = {},
            modifier = Modifier
                .weight(1f)
                .background(
                color = Color(0xffb9b9b9),
                shape = RoundedCornerShape(percent = 10)
            ))
    }
}

@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
    paddingLeadingIconEnd: Dp = 0.dp,
    paddingTrailingIconStart: Dp = 0.dp,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null
) {
    //val state = savedInstanceState(saver = TextFieldValue.Saver) { TextFieldValue() }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        if (leadingIcon != null) {
            leadingIcon()
        }
        Box(
            modifier = Modifier.weight(1f)
                .padding(start = paddingLeadingIconEnd, end = paddingTrailingIconStart)
        ) {
            TextField(
                modifier = Modifier.background(
                    color = Color(0xffb9b9b9),
                    shape = RoundedCornerShape(percent = 10)
                ),
                value = "HOLA",
                onValueChange = { //state.value = it
             }
            )
        }
        if (trailingIcon != null) {
            trailingIcon()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewNewLesson() {
    //NewLessonScreen()
}