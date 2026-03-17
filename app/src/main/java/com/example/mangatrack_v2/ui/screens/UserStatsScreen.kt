package com.example.mangatrack_v2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mangatrack_v2.ui.components.StatCard
import com.example.mangatrack_v2.util.MangaStatus
import com.example.mangatrack_v2.viewmodel.MangaViewModel

@Composable
fun UserStatsScreen(
    navController: NavController,
    viewModel: MangaViewModel = viewModel()
) {

    val mangas = viewModel.mangas.collectAsState(initial = emptyList())

    val total = mangas.value.size
    val completed = mangas.value.count { it.status == MangaStatus.COMPLETED }
    val reading = mangas.value.count { it.status == MangaStatus.READING }
    val planned = mangas.value.count { it.status == MangaStatus.PLANNED }

    val completionRate =
        if (total == 0) 0
        else (completed * 100) / total

    val chaptersRead = mangas.value.sumOf { it.chaptersRead }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row {

            StatCard(
                title = "Total",
                value = total.toString()
            )

            StatCard(
                title = "Completed",
                value = completed.toString()
            )

        }

        Row {

            StatCard(
                title = "Reading",
                value = reading.toString()
            )

            StatCard(
                title = "Planned",
                value = planned.toString()
            )

        }

        Row {

            StatCard(
                title = "Completion %",
                value = "$completionRate%"
            )

            StatCard(
                title = "Chapters Read",
                value = chaptersRead.toString()
            )

        }

    }

}