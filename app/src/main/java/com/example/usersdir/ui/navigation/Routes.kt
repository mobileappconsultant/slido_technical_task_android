package com.example.usersdir.ui.navigation

sealed class Routes(val route: String) {
    object Home : Routes("homeScreen")
    object CreateUser : Routes("createScreen")
}
