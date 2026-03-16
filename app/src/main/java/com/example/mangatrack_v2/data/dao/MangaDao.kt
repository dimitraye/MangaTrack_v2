package com.example.mangatrack_v2.data.dao

import androidx.room.*
import com.example.mangatrack_v2.data.entity.MangaEntity

@Dao
interface MangaDao {

    @Query("SELECT * FROM manga")
    suspend fun getAllMangas(): List<MangaEntity>

    @Insert
    suspend fun insertManga(manga: MangaEntity)

    @Update
    suspend fun updateManga(manga: MangaEntity)

    @Delete
    suspend fun deleteManga(manga: MangaEntity)
}