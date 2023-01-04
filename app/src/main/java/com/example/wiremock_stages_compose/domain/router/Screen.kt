package com.example.wiremock_stages_compose.domain.router

sealed class Screen(val route: String) {
    object Main: Screen(Route.Main)
    object Stage: Screen(Route.Stage)
}