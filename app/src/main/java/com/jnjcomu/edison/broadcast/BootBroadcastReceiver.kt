package com.jnjcomu.edison.broadcast

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import java.util.*

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-08-30
 */

class BootBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            val alarmIntent = Intent(context, EdisonReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0)

            val manager1 = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val calendar1 = Calendar.getInstance()
            calendar1.setTimeInMillis(System.currentTimeMillis())
            calendar1.set(Calendar.HOUR_OF_DAY, 19)
            calendar1.set(Calendar.MINUTE, 50)
            calendar1.set(Calendar.SECOND, 0)

            manager1.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent)

            val manager2 = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val calendar2 = Calendar.getInstance()
            calendar2.setTimeInMillis(System.currentTimeMillis())
            calendar2.set(Calendar.HOUR_OF_DAY, 21)
            calendar2.set(Calendar.MINUTE, 40)
            calendar2.set(Calendar.SECOND, 0)

            manager2.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent)
        }
    }
}