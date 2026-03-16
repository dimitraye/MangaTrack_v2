package com.example.mangatrack_v2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangatrack_v2.data.entity.MangaEntity
import com.example.mangatrack_v2.data.repository.MangaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MangaViewModel (
    private val repository: MangaRepository
) : ViewModel() {

    private val _mangas = MutableStateFlow<List<MangaEntity>>(emptyList())
    val mangas: StateFlow<List<MangaEntity>> = _mangas

    fun loadMangas() {
        viewModelScope.launch {
            _mangas.value = repository.getAllMangas()
        }
    }

    fun addManga(manga: MangaEntity) {
        viewModelScope.launch {
            repository.insertManga(manga)
            loadMangas()
        }
    }

    fun updateManga(manga: MangaEntity) {
        viewModelScope.launch {
            repository.updateManga(manga)
            loadMangas()
        }
    }

    fun deleteManga(manga: MangaEntity) {
        viewModelScope.launch {
            repository.deleteManga(manga)
            loadMangas()
        }
    }
}