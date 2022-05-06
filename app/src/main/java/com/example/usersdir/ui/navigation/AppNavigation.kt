package com.example.usersdir.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.usersdir.ui.screens.home.HomeScreen
import com.example.usersdir.ui.screens.create.CreateUserScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Home.route) {
        composable(route = Routes.Home.route) {
            HomeScreen {
                navController.navigate(Routes.CreateUser.route)
            }
        }

        composable(
            route = Routes.CreateUser.route
        ) {
            CreateUserScreen {
                navController.popBackStack()
            }
        }
    }
}
