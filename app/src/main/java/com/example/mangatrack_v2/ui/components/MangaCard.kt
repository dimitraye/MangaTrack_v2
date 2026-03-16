package com.example.mangatrack_v2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mangatrack_v2.data.entity.MangaEntity

@Composable
fun MangaCard(
    manga: MangaEntity
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(text = manga.title)

            Text(text = "Status: ${manga.status}")

            Text(text = "Chapters: ${manga.chaptersRead} / ${manga.chaptersTotal ?: "?"}")

        }
    }
}