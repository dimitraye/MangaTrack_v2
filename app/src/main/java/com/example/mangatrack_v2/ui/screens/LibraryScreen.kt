package com.example.mangatrack_v2.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mangatrack_v2.ui.components.MangaCard
import com.example.mangatrack_v2.viewmodel.MangaViewModel

@Composable
fun LibraryScreen(navController: NavController, viewModel: MangaViewModel = viewModel()) {

    val mangas = viewModel.mangas.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(mangas.value) { manga ->

            MangaCard(manga)

        }

    }
}