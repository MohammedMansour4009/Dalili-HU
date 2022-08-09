package com.buyin.dalili.core.service

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.buyin.dalili.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        //Here we can handle the pushed notification
        //message will have the content that is pushed by the firebase

        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setContentTitle(remoteMessage.notification?.title)
            .setContentText(remoteMessage.notification?.body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigTextStyle())
            .setSound(
                RingtoneManager.getDefaultUri(
                    RingtoneManager.TYPE_NOTIFICATION
                )
            )
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true);

        val notificationManager =
            (getSystemService(Context.NOTIFICATION_SERVICE) as (NotificationManager));

        notificationManager.notify(0, notificationBuilder.build());
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("onNewToken", "onCreate: firebase token start here :  $token")
    }

}