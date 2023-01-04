package com.example.wiremock_stages_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wiremock_stages_compose.domain.router.Screen
import com.example.wiremock_stages_compose.ui.screen.MainScreen
import com.example.wiremock_stages_compose.ui.screen.StageScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen(navController)
        }
        composable(
            route = Screen.Stage.route + "/{paymentCode}",
            arguments = listOf(navArgument("paymentCode") {
                type = NavType.StringType
            })
        ) {
            StageScreen(navController, it.arguments?.getString("paymentCode")!!)
        }
    }
}