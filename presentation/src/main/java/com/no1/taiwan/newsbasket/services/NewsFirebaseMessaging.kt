package com.no1.taiwan.newsbasket.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.media.RingtoneManager.getDefaultUri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import androidx.core.app.NotificationCompat
import com.devrapid.kotlinknifer.logw
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.no1.taiwan.newsbasket.R
import kotlin.random.Random

class NewsFirebaseMessaging : FirebaseMessagingService() {
    companion object {
        private val TAG = "MyFMService"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        logw("From: " + remoteMessage.data)

        // Handle data payload of FCM messages.
        remoteMessage.data?.let(::handleNotification)
    }

    private fun handleNotification(data: Map<String, String>) {
        val title = data["news_title"].orEmpty()
        val content = data["news_body"].orEmpty()
        val icon = R.drawable.ic_star
//        val intent = Intent(this, MainV2Activity::class.java).apply {
//            addFlags(FLAG_ACTIVITY_CLEAR_TOP)
//            putExtras(bundleOf(PARAMS_REQUEST_CODE to requestCode))
//        }
//        val pendingIntent = getActivity(this, notificationId, intent, FLAG_ONE_SHOT)
        val defaultSoundUri = getDefaultUri(TYPE_NOTIFICATION)
        val channelId = getString(R.string.gcm_defaultSenderId)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(content)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
//            .setContentIntent(pendingIntent)

        sendNotification(notificationBuilder)
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     */
    private fun sendNotification(builder: NotificationCompat.Builder) {
        val notificationId = Random.nextInt()
        val channelId = getString(R.string.gcm_defaultSenderId)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (SDK_INT >= O) {
            val channel =
                NotificationChannel(channelId, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(notificationId, builder.build())
    }
}
