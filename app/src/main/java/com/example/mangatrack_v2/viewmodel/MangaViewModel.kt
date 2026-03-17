package com.example.mangatrack_v2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangatrack_v2.data.entity.MangaEntity
import com.example.mangatrack_v2.data.repository.MangaRepository
import com.example.mangatrack_v2.database.MangaDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MangaViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = MangaDatabase.getDatabase(application).mangaDao()

    private val repository = MangaRepository(dao)

    val mangas: Flow<List<MangaEntity>> = repository.getAllMangas()

    fun addManga(manga: MangaEntity) {
        viewModelScope.launch {
            repository.insertManga(manga)
        }
    }

    fun updateManga(manga: MangaEntity) {
        viewModelScope.launch {
            repository.updateManga(manga)
        }
    }

    fun deleteManga(manga: MangaEntity) {
        viewModelScope.launch {
            repository.deleteManga(manga)
        }
    }
}