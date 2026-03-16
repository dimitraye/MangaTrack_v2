package com.example.mangatrack_v2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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

    val totalChaptersRead = mangas.value.sumOf { it.chaptersRead }

    val completionRate =
        if (total == 0) 0
        else (completed * 100) / total

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "User Statistics",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Total mangas: $total")

        Text("Completed mangas: $completed")

        Text("Reading mangas: $reading")

        Text("Planned mangas: $planned")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Completion rate: $completionRate %")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Total chapters read: $totalChaptersRead")

    }

}