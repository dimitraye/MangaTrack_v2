package com.example.mangatrack_v2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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

            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {

                items(recentMangas) { manga ->

                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(150.dp)
                            .height(120.dp),

                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {

                            Text(
                                text = manga.title,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "${manga.chaptersRead}/${manga.chaptersTotal ?: "?"}",
                                style = MaterialTheme.typography.bodySmall
                            )

                        }

                    }

                }

            }

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