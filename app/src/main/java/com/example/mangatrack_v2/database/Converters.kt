package com.example.mangatrack_v2.database

import androidx.room.TypeConverter
import com.example.mangatrack_v2.util.MangaStatus

class Converters {
    @TypeConverter
    fun fromStatus(status: MangaStatus): String {
        return status.name
    }

    @TypeConverter
    fun toStatus(status: String): MangaStatus {
        return MangaStatus.valueOf(status)
    }
}