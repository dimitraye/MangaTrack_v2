package com.example.mangatrack_v2.ui.navigation

object Routes  {

    const val HOME = "home"
    const val LIBRARY = "library"
    const val MANGA_DETAILS = "manga_details/{mangaId}"
    const val ADD_MANGA = "add_manga"
    const val PROFILE = "profile"
    const val SETTINGS = "settings"
    const val USER_STATS = "user_stats"

    const val EDIT_MANGA = "edit_manga/{mangaId}"

    fun editManga(mangaId: Long) = "edit_manga/$mangaId"
    fun mangaDetails(mangaId: Long) = "manga_details/$mangaId"
}