package com.example.mangatrack_v2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mangatrack_v2.viewmodel.MangaViewModel

@Composable
fun EditMangaScreen(
    navController: NavController,
    mangaId: Long?,
    viewModel: MangaViewModel = viewModel()
) {

    val mangas = viewModel.mangas.collectAsState(initial = emptyList())
    val manga = mangas.value.find { it.id == mangaId }

    var chaptersRead by remember { mutableStateOf("") }

    if (manga != null) {

        var chaptersRead by remember { mutableStateOf("") }

        LaunchedEffect(manga) {
            if (manga != null) {
                chaptersRead = manga.chaptersRead.toString()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text("Edit Manga", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text(manga.title)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = chaptersRead,
                onValueChange = { chaptersRead = it },
                label = { Text("Chapters read") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    val updated = manga.copy(
                        chaptersRead = chaptersRead.toIntOrNull() ?: 0
                    )

                    viewModel.updateManga(updated)

                    navController.popBackStack()

                }
            ) {

                Text("Save")

            }

        }

    }

}