package com.jnjcomu.edison.broadcast

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.jnjcomu.edison.R
import com.jnjcomu.edison.activity.SplashActivity
import com.jnjcomu.edison.storage.AppSettingStorage

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-24
 */

class EdisonReceiver : BroadcastReceiver() {

    var context: Context? = null

    override fun onReceive(context: Context, intent: Intent) {
        this.context = context

        if(AppSettingStorage(context).isActiveScanning) {
            noti("지금 어디 계신가요?")
        }
    }

    private fun noti(msg: String) {
        val builder = NotificationCompat.Builder(context)
                .setContentTitle("Edison")
                .setContentText(msg)
                .setSmallIcon(R.drawable.logo_white)
        val notifyIntent = Intent(context, SplashActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, 0)
        builder.setContentIntent(pendingIntent)
        val managerCompat = NotificationManagerCompat.from(context)
        managerCompat.notify(777, builder.build())
    }
}