package com.example.mangatrack_v2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mangatrack_v2.ui.navigation.Routes
import com.example.mangatrack_v2.viewmodel.MangaViewModel

@Composable
fun MangaDetailsScreen(
    navController: NavController,
    mangaId: Long?,
    viewModel: MangaViewModel = viewModel()
) {

    val mangas = viewModel.mangas.collectAsState(initial = emptyList())

    val manga = mangas.value.find { it.id == mangaId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        if (manga != null) {

            Text(text = manga.title)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Status: ${manga.status}")

            Text(text = "Chapters: ${manga.chaptersRead} / ${manga.chaptersTotal ?: "?"}")

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.deleteManga(manga)
                    navController.popBackStack()
                }
            ) {

                Text("Delete")

            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate(
                        Routes.editManga(manga.id)
                    )
                }
            ) {
                Text("Edit")
            }

        } else {

            Text("Manga not found")

        }

    }

}