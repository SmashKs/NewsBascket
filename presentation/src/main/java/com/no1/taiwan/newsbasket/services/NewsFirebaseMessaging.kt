package com.no1.taiwan.newsbasket.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.media.RingtoneManager.getDefaultUri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.devrapid.kotlinknifer.logw
import com.devrapid.kotlinshaver.bkg
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.news.AddLocalNewsWrapUsecase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import kotlin.random.Random
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenWrapUsecase.Request as KeepNewsTokenRequest

class NewsFirebaseMessaging : FirebaseMessagingService(), KodeinAware {
    /** A Kodein Aware class must be within reach of a [Kodein] object. */
    override val kodein by closestKodein()
    private val keepTokenCase by instance<KeepNewsTokenWrapUsecase>()
    private val addNewsCase by instance<AddLocalNewsWrapUsecase>()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Keep the firebase token into mmkv storage.
        bkg { keepTokenCase.execute(KeepNewsTokenRequest(TokenParams(firebaseToken = token))) }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        logw("From: " + remoteMessage.data)

        // Handle data payload of FCM messages.
        remoteMessage.data?.let(::handleNotification)
    }

    private fun handleNotification(data: Map<String, String>) {
        val title = data["news_title"].orEmpty()
        val content = data["news_body"].orEmpty()
        val newsUrl = data["news_url"].orEmpty()
        val icon = R.drawable.ic_star
        val intent = Intent(Intent.ACTION_VIEW, newsUrl.toUri())
        val pendingIntent = getActivity(this, 0, intent, FLAG_ONE_SHOT)
        val defaultSoundUri = getDefaultUri(TYPE_NOTIFICATION)
        val channelId = getString(R.string.gcm_defaultSenderId)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(content)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        sendNotification(notificationBuilder)

        // Insert the new news into local database.
        bkg {
            addNewsCase.execute(AddLocalNewsWrapUsecase.Request(NewsParams(
                content = content,
                url = newsUrl,
                title = title
            )))
        }
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
