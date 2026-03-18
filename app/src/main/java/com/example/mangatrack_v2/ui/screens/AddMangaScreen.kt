package com.example.mangatrack_v2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mangatrack_v2.data.entity.MangaEntity
import com.example.mangatrack_v2.util.MangaStatus
import com.example.mangatrack_v2.viewmodel.MangaViewModel
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import coil.compose.AsyncImage

@Composable
fun AddMangaScreen(
    navController: NavController,
    viewModel: MangaViewModel = viewModel()
) {

    var title by remember { mutableStateOf("") }
    var chaptersTotal by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Add Manga", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                imagePickerLauncher.launch("image/*")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select Image")
        }
        imageUri?.let {
            AsyncImage(
                model = it,
                contentDescription = "Selected image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = chaptersTotal,
            onValueChange = { chaptersTotal = it },
            label = { Text("Total chapters") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val manga = MangaEntity(
                    title = title,
                    status = MangaStatus.PLANNED,
                    chaptersRead = 0,
                    chaptersTotal = chaptersTotal.toIntOrNull(),
                    author = null,
                    artist = null,
                    genre = null,
                    rating = null,
                    review = null,
                    coverImagePath = imageUri?.toString(),
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )

                viewModel.addManga(manga)

                navController.popBackStack()

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Save")

        }

    }

}