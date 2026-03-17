package com.example.mangatrack_v2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {

        @Volatile
        private var INSTANCE: MangaDatabase? = null

        fun getDatabase(context: Context): MangaDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MangaDatabase::class.java,
                    "manga_database"
                ).build()

                INSTANCE = instance

                instance
            }

        }

    }

}