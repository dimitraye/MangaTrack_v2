package com.example.mangatrack_v2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mangatrack_v2.ui.components.StatCard
import com.example.mangatrack_v2.util.MangaStatus
import com.example.mangatrack_v2.viewmodel.MangaViewModel
import android.content.Context
import com.example.mangatrack_v2.notification.ReminderScheduler
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import android.app.Activity
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.example.mangatrack_v2.ui.navigation.Routes
import com.example.mangatrack_v2.util.PdfExporter
import android.content.Intent
import com.example.mangatrack_v2.notification.PdfNotificationHelper

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
    val context = LocalContext.current
    val activity = context as? Activity
    val now = System.currentTimeMillis()
    val upcomingReminders = mangas.value
        .filter { it.reminderTime != null && it.reminderTime > now }
        .sortedBy { it.reminderTime }
        .take(3)

    LaunchedEffect(Unit) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                activity?.let {
                    ActivityCompat.requestPermissions(
                        it,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        1
                    )
                }
            }

        }

    }


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

        item {

            Button(
                onClick = {
                    navController.navigate(Routes.ADD_MANGA)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add your first manga")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {

            Button(
                onClick = {

                    Log.d("PDF", "Export start")

                    try {

                        val uri = PdfExporter.exportMangas(
                            context,
                            mangas.value
                        )

                        Log.d("PDF", "URI: $uri")

                        Toast.makeText(
                            context,
                            "PDF généré ✔",
                            Toast.LENGTH_SHORT
                        ).show()

                        // 🔥 OUVRIR LE PDF
                        uri?.let {

                            PdfNotificationHelper.showDownloadNotification(
                                context,
                                it
                            )

                        }

                    } catch (e: Exception) {

                        Log.e("PDF", "ERROR", e)

                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Export PDF")
            }

        }

        // 🔥 SECTION 2 — Notifications (placeholder)
        item {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Upcoming Reminders",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (upcomingReminders.isEmpty()) {

                Text("No reminders scheduled")

            } else {

                upcomingReminders.forEach { manga ->

                    val timeLeft = (manga.reminderTime!! - now) / 1000

                    val displayText = when {
                        timeLeft < 60 -> "in a few seconds"
                        timeLeft < 3600 -> "in ${timeLeft / 60} min"
                        timeLeft < 86400 -> "in ${timeLeft / 3600} h"
                        else -> "in ${timeLeft / 86400} days"
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {

                            Text(
                                text = manga.title,
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Text(
                                text = displayText,
                                style = MaterialTheme.typography.bodySmall
                            )

                        }

                    }

                }

            }

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