package com.example.mangatrack_v2.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.compose.*

import com.example.mangatrack_v2.ui.screens.*

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.HOME) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.LIBRARY) },
                    icon = { Icon(Icons.Default.Book, contentDescription = "Library") },
                    label = { Text("Library") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.USER_STATS) },
                    icon = { Icon(Icons.Default.BarChart, contentDescription = "Stats") },
                    label = { Text("Stats") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.PROFILE) },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Routes.SETTINGS) },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )

            }

        }

    ) { paddingValues ->

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

            composable(Routes.USER_STATS) {
                UserStatsScreen(navController)
            }

            composable(Routes.PROFILE) {
                ProfileScreen(navController)
            }

            composable(Routes.SETTINGS) {
                SettingsScreen(navController)
            }

            composable(Routes.ADD_MANGA) {
                AddMangaScreen(navController)
            }

            composable(Routes.MANGA_DETAILS) { backStackEntry ->

                val mangaId = backStackEntry.arguments
                    ?.getString("mangaId")
                    ?.toLongOrNull()

                MangaDetailsScreen(
                    navController = navController,
                    mangaId = mangaId
                )

            }

            composable(Routes.EDIT_MANGA) { backStackEntry ->

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

}