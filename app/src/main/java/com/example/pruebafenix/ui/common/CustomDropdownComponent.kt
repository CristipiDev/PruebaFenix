package com.example.pruebafenix.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomDropdownComponent(
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