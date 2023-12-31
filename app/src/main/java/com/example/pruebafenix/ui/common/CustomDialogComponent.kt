package com.example.pruebafenix.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialogComponent (
    value: String,
    setShowDialog: (Boolean) -> Unit,
    setValue: (String) -> Unit,
    lessonId: Long,
    setIntoDb: (String, Long) -> Unit
){

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(5.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(4f),
                            text = stringResource(com.example.pruebafenix.R.string.add_new_student),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .weight(1f)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    CustomBasicTextFieldComponent(
                        valueTextField = value,
                        onChangeValue = setValue ,
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        keyboardType = KeyboardType.Text
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    CustomButtonComponent(
                        text = stringResource(com.example.pruebafenix.R.string.save_button),
                        {
                            setIntoDb(value, lessonId)
                            setShowDialog(false)
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

@Preview(showBackground = true)
@Composable
fun PreviewDialog() {
    //CustomDialogComponent("Hola mundo", {}, {}, 0, {})
}