package com.example.mangatrack_v2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mangatrack_v2.data.dao.MangaDao
import com.example.mangatrack_v2.data.entity.MangaEntity

@Database(
    entities = [MangaEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MangaDatabase : RoomDatabase() {

    abstract fun mangaDao(): MangaDao
}