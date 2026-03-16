package com.example.mangatrack_v2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangatrack_v2.data.entity.MangaEntity
import com.example.mangatrack_v2.data.repository.MangaRepository
import com.example.mangatrack_v2.util.MangaStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MangaViewModel(
    private val repository: MangaRepository
) : ViewModel() {

    val mangas: Flow<List<MangaEntity>> = repository.getAllMangas()

    fun addManga(manga: MangaEntity) {
        viewModelScope.launch {
            repository.insertManga(manga)
        }
    }

    fun deleteManga(manga: MangaEntity) {
        viewModelScope.launch {
            repository.deleteManga(manga)
        }
    }

    init {
        viewModelScope.launch {
            repository.insertManga(
                MangaEntity(
                    title = "Test Manga",
                    status = MangaStatus.PLANNED,
                    chaptersRead = 0,
                    chaptersTotal = 100,
                    author = null,
                    artist = null,
                    genre = null,
                    rating = null,
                    review = null,
                    coverImagePath = null,
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
            )
        }
    }
}