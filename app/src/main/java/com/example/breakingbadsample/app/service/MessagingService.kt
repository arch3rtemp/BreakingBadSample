package com.example.breakingbadsample.app.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.breakingbadsample.R
import com.example.breakingbadsample.domain.use_cases.SelectCharacterByIdUseCase
import com.example.breakingbadsample.domain.use_cases.SetFcmTokenUseCase
import com.example.breakingbadsample.presentation.main.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class MessagingService : FirebaseMessagingService() {
    private val setFcmTokenUseCase by inject<SetFcmTokenUseCase>()
    private val selectCharacterByIdUseCase by inject<SelectCharacterByIdUseCase>()

    private val compositeDisposable = CompositeDisposable()
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.v("TestToken", p0)
        compositeDisposable.add(
            setFcmTokenUseCase.start(p0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.v("TestToken", p0.data["charId"] ?: "")
        val type = p0.data["pushType"]?.toInt() ?: -1
        when (type) {
            PUSH_TYPE_CHAR -> {
                val charId = p0.data["charId"]!!
                sendNotification(charId)
            }
        }
    }

    private fun sendNotification(charId: String) {
        compositeDisposable.add(
            selectCharacterByIdUseCase.start(charId.toInt())
                .take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { character ->
                    val intent = Intent(this, MainActivity::class.java)
                    val notificationManager =
                        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationID = charId.toInt()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        createNotificationChannel(notificationManager)
                    }
                    intent.putExtra(KEY_CHAR_ID, charId)
                    val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
                    } else {
                        PendingIntent.getActivity(
                            this,
                            0,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                        )
                    }

                    val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle(character.name)
                        .setContentText(character.nickname)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .build()

                    notificationManager.notify(notificationID, notification)
                })

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "FCM channel"
            enableLights(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val PUSH_TYPE_CHAR = 1
        const val KEY_CHAR_ID = "charId"
        private const val CHANNEL_ID = "fcm_channel_id"
        private const val CHANNEL_NAME = "fcm_channel_name"
    }
}