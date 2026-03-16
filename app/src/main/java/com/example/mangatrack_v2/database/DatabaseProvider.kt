package com.example.mangatrack_v2.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    private var INSTANCE: MangaDatabase? = null

    fun getDatabase(context: Context): MangaDatabase {

        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MangaDatabase::class.java,
                "mangatrack_database"
            ).build()
        }

        return INSTANCE!!
    }
}