package com.example.mangatrack_v2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mangatrack_v2.util.MangaStatus

@Entity(tableName = "manga")
data class MangaEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String,

    val author: String?,

    val artist: String?,

    val genre: String?,

    val status: MangaStatus,

    val chaptersRead: Int,

    val chaptersTotal: Int?,

    val rating: Int?,

    val review: String?,

    val coverImagePath: String?,

    val createdAt: Long,

    val updatedAt: Long
)