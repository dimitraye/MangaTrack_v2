package com.example.mangatrack_v2.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.PendingIntentCompat
import com.example.mangatrack_v2.R

object PdfNotificationHelper {

    fun showDownloadNotification(
        context: Context,
        uri: Uri
    ) {

        val channelId = "pdf_download_channel"

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 🔥 Channel (Android 8+)
        val channel = NotificationChannel(
            channelId,
            "PDF Downloads",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)

        // 🔥 Intent pour ouvrir le PDF
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val pendingIntent = PendingIntentCompat.getActivity(
            context,
            0,
            intent,
            0,
            false
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Download complete")
            .setContentText("Your manga PDF is ready")
            .setSmallIcon(android.R.drawable.stat_sys_download_done)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}