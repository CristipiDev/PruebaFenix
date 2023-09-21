package com.example.pruebafenix.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pruebafenix.ui.calendarpage.CalendarScreen
import com.example.pruebafenix.ui.calendarpage.CalendarViewModel
import com.example.pruebafenix.ui.navigation.Navigation
import com.example.pruebafenix.ui.theme.PruebaFenixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = CalendarViewModel()
        setContent {
            PruebaFenixTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

