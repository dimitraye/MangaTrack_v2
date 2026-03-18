package com.example.mangatrack_v2.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mangatrack_v2.data.entity.MangaEntity
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import com.example.mangatrack_v2.util.MangaStatus
import androidx.compose.ui.graphics.Color

@Composable
fun MangaCard(
    manga: MangaEntity,
    onClick: () -> Unit
) {

    val progress = if (manga.chaptersTotal != null && manga.chaptersTotal > 0) {
        (manga.chaptersRead.toFloat() / manga.chaptersTotal).coerceIn(0f, 1f)
    } else {
        0f
    }

    val statusColor = when (manga.status) {
        MangaStatus.READING -> Color(0xFF2196F3)   // bleu
        MangaStatus.COMPLETED -> Color(0xFF4CAF50) // vert
        MangaStatus.PLANNED -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = manga.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 🔥 Progress bar
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth(),
                color = statusColor
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${manga.chaptersRead} / ${manga.chaptersTotal ?: "?"}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = manga.status.name
                    .lowercase()
                    .replaceFirstChar { it.uppercase() },
                color = statusColor,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}