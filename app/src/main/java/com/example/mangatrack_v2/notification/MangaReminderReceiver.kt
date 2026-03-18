package com.example.mangatrack_v2.notification

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class MangaReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val title = intent.getStringExtra("title") ?: "Manga Reminder"
        val message = intent.getStringExtra("message") ?: "Time to read!"

        val channelId = "manga_reminder_channel"

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 🔥 créer le channel (obligatoire Android 8+)
        val channel = NotificationChannel(
            channelId,
            "Manga Reminders",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_dialog_info)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}