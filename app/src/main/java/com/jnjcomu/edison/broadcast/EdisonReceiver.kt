package com.jnjcomu.edison.broadcast

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.jnjcomu.edison.R
import com.jnjcomu.edison.activity.CheckInActivity
import com.jnjcomu.edison.storage.AppSettingStorage

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-08-30
 */

class EdisonReceiver : BroadcastReceiver() {

    var context: Context? = null

    override fun onReceive(context: Context, intent: Intent) {
        this.context = context

        if(AppSettingStorage(context).isActiveScanning) {
            noti()
        }
    }

    private fun noti() {
        val builder = NotificationCompat.Builder(context)
                .setContentTitle("Edison")
                .setContentText("체크인할 시간입니다!")
                .setSmallIcon(R.drawable.logo_white)
        val notifyIntent = Intent(context, CheckInActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, 0)
        builder.setContentIntent(pendingIntent)
        val managerCompat = NotificationManagerCompat.from(context!!)
        managerCompat.notify(777, builder.build())
    }
}