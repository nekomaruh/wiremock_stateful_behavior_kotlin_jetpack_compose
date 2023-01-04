package com.example.wiremock_stages_compose.ui.widget

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun AppBar(text: String) = TopAppBar(
    title = { Text(text = text) }
)