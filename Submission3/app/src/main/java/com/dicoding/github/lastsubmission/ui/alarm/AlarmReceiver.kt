package com.dicoding.github.lastsubmission.ui.alarm

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.ui.main.MainActivity
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val REPEAT_ALARM_ID = 101
    }

    override fun onReceive(mContext: Context, intent: Intent?) {
        showNotification(mContext)
    }

    private fun showNotification(mContext: Context) {
        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "Repeat_scheduler"

        val title = mContext.getString(R.string.app_name)
        val message = mContext.getString(R.string.settings)

        val intent = Intent(mContext, MainActivity::class.java)

        val pendingIntent = TaskStackBuilder.create(mContext)
            .addParentStack(MainActivity::class.java)
            .addNextIntent(intent)
            .getPendingIntent(REPEAT_ALARM_ID, PendingIntent.FLAG_UPDATE_CURRENT)


        val notificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder  = NotificationCompat.Builder(mContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
            .setContentIntent(pendingIntent)
            .setContentText(message)
            .setContentText(title)
            .setColor(ContextCompat.getColor(mContext, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
            .setAutoCancel(true)


        if (SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = builder.build()

        notificationManager.notify(REPEAT_ALARM_ID, notification)
    }

    fun setRepeat(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent: PendingIntent = Intent(context, AlarmReceiver::class.java).let {
            PendingIntent.getBroadcast(context, REPEAT_ALARM_ID, it, 0)
        }

        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 20)
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            intent
        )
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent =  PendingIntent.getBroadcast(context, REPEAT_ALARM_ID, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        Toast.makeText(context, context.getString(R.string.alarm_canceled), Toast.LENGTH_SHORT).show()
    }
}