package com.example.mangatrack_v2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mangatrack_v2.ui.components.StatCard
import com.example.mangatrack_v2.util.MangaStatus
import com.example.mangatrack_v2.viewmodel.MangaViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MangaViewModel = viewModel()
) {

    val mangas = viewModel.mangas.collectAsState(initial = emptyList())

    val recentMangas = mangas.value.takeLast(5)

    val total = mangas.value.size
    val reading = mangas.value.count { it.status == MangaStatus.READING }
    val completed = mangas.value.count { it.status == MangaStatus.COMPLETED }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔥 SECTION 1 — Recent mangas
        item {
            Text(
                text = "Recent mangas",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        items(recentMangas) { manga ->
            Text(text = manga.title)
        }

        // 🔥 SECTION 2 — Notifications (placeholder)
        item {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Notifications",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("No notifications yet")
        }

        // 🔥 SECTION 3 — Stats résumé
        item {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Quick Stats",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Row {
                StatCard("Total", total.toString())
                StatCard("Reading", reading.toString())
            }
        }

        item {
            Row {
                StatCard("Completed", completed.toString())
            }
        }

    }

}