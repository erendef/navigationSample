package com.definex.navigation

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object Notifier {

    private const val  channelId = "Default"

    fun init(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificatonManager = activity.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            val existingChannel = notificatonManager.getNotificationChannel(channelId)
            if (existingChannel == null) {
                val name = "Notification default"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(channelId, name, importance)
                mChannel.description = "Notification from Nav App"
                notificatonManager.createNotificationChannel(mChannel)
            }
        }
    }

    fun postNotification(id: Long, context: Context, intent: PendingIntent) {
        val builder = NotificationCompat.Builder(context, channelId)
        builder.setContentTitle("DeepLink Sample").setSmallIcon(R.drawable.ic_launcher_background)
        val text = "Deeplink Sample Text"
        val notification = builder.setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(intent)
            .setAutoCancel(true)
            .build()

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.cancelAll()
        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        notificationManager.notify(id.toInt(),notification)
    }
}