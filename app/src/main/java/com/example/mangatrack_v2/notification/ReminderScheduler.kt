package com.example.mangatrack_v2.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

object ReminderScheduler {

    fun scheduleReminder(
        context: Context,
        timeInMillis: Long,
        title: String,
        message: String
    ) {

        val intent = Intent(context, MangaReminderReceiver::class.java).apply {
            putExtra("title", title)
            putExtra("message", message)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            pendingIntent
        )
    }
}