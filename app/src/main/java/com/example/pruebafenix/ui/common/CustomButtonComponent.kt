package com.example.pruebafenix.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CustomButtonComponent(
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