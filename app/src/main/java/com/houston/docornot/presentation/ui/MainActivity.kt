package com.houston.docornot.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.houston.docornot.presentation.ui.screens.LoginScreen
import com.houston.docornot.presentation.ui.theme.DocOrNotTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DocOrNotTheme {
                LoginScreen()
            }
        }
    }
}
