package com.example.pruebafenix.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.pruebafenix.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomBasicTextFieldComponent (
    valueTextField: String,
    onChangeValue: (String) -> Unit,
    modifier: Modifier,
    keyboardType: KeyboardType
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    BasicTextField(
        maxLines = 1,
        modifier = modifier,
        value = valueTextField,
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