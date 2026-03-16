package com.example.mangatrack_v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mangatrack_v2.ui.navigation.AppNavigation
import com.example.mangatrack_v2.ui.theme.MangaTrack_v2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MangaTrack_v2Theme {
                AppNavigation()
            }
        }
    }
}