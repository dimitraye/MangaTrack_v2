package com.example.mangatrack_v2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mangatrack_v2.ui.screens.HomeScreen
import com.example.mangatrack_v2.ui.screens.LibraryScreen
import com.example.mangatrack_v2.ui.screens.ProfileScreen
import com.example.mangatrack_v2.ui.screens.SettingsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        composable(Routes.LIBRARY) {
            LibraryScreen(navController)
        }

        composable(Routes.PROFILE) {
            ProfileScreen(navController)
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(navController)
        }

        composable(Routes.USER_STATS) {
            SettingsScreen(navController)
        }


    }
}