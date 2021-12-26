package com.amanullah.alarmclock.activity

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.amanullah.alarmclock.R
import androidx.test.core.app.ApplicationProvider.getApplicationContext

import android.media.RingtoneManager

import android.media.Ringtone
import android.net.Uri
import androidx.test.core.app.ApplicationProvider


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val track = RingtoneManager.getRingtone(context,alarm)
        track.play()

        val i = Intent(context, DestinationActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)

        val builder = NotificationCompat.Builder(context!!, "amanullah")
            .setSmallIcon(R.drawable.notifications)
            .setContentTitle("Amanullah")
            .setContentText("Alarm Manager from Amanullah")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123, builder.build())
    }
}