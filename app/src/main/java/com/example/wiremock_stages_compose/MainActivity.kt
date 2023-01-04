package com.example.wiremock_stages_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wiremock_stages_compose.ui.navigation.Navigation
import com.example.wiremock_stages_compose.ui.theme.Wiremock_stages_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Wiremock_stages_composeTheme {
                Navigation()
            }
        }
    }
}