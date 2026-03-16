package com.example.mangatrack_v2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mangatrack_v2.ui.screens.AddMangaScreen
import com.example.mangatrack_v2.ui.screens.EditMangaScreen
import com.example.mangatrack_v2.ui.screens.HomeScreen
import com.example.mangatrack_v2.ui.screens.LibraryScreen
import com.example.mangatrack_v2.ui.screens.MangaDetailsScreen
import com.example.mangatrack_v2.ui.screens.ProfileScreen
import com.example.mangatrack_v2.ui.screens.SettingsScreen
import com.example.mangatrack_v2.ui.screens.UserStatsScreen

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
            UserStatsScreen(navController)
        }

        composable(Routes.ADD_MANGA) {
            AddMangaScreen(navController)
        }

        composable(
            route = Routes.MANGA_DETAILS
        ) { backStackEntry ->

            val mangaId = backStackEntry.arguments
                ?.getString("mangaId")
                ?.toLongOrNull()

            MangaDetailsScreen(
                navController = navController,
                mangaId = mangaId
            )

        }

        composable(
            route = Routes.EDIT_MANGA
        ) { backStackEntry ->

            val mangaId = backStackEntry.arguments
                ?.getString("mangaId")
                ?.toLongOrNull()

            EditMangaScreen(
                navController = navController,
                mangaId = mangaId
            )

        }


    }
}