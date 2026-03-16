package com.example.mangatrack_v2.data.repository

import com.example.mangatrack_v2.data.dao.MangaDao
import com.example.mangatrack_v2.data.entity.MangaEntity

class MangaRepository (private val mangaDao: MangaDao) {

    fun getAllMangas() = mangaDao.getAllMangas()

    suspend fun insertManga(manga: MangaEntity) {
        mangaDao.insertManga(manga)
    }

    suspend fun updateManga(manga: MangaEntity) {
        mangaDao.updateManga(manga)
    }

    suspend fun deleteManga(manga: MangaEntity) {
        mangaDao.deleteManga(manga)
    }
}