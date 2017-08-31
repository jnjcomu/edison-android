package com.jnjcomu.edison.broadcast

import android.app.PendingIntent
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.jnjcomu.edison.R
import com.jnjcomu.edison.activity.SplashActivity
import com.jnjcomu.edison.addition.EdisonAPI
import com.jnjcomu.edison.callback.CloudEventListener
import com.loplat.placeengine.Plengi
import com.loplat.placeengine.PlengiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-08-30
 */

class EdisonReceiver : BroadcastReceiver(), CloudEventListener {

    var context: Context? = null

    override fun onReceive(context: Context, intent: Intent) {
        Plengi.getInstance(context).refreshPlace()
    }

    override fun onPlaceDefault(response: PlengiResponse) {
        EdisonAPI.checkin(response.place.name).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                val builder = NotificationCompat.Builder(context)
                builder.setContentTitle("Edison")
                builder.setContentText("위치 정보를 전송했습니다.")
                builder.setSmallIcon(R.mipmap.ic_launcher)
                val notifyIntent = Intent(context, SplashActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(context, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
                builder.setContentIntent(pendingIntent)
                val notificationCompat = builder.build()
                val managerCompat = NotificationManagerCompat.from(context)
                managerCompat.notify(777, notificationCompat)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
            }

        })
    }

    override fun onPlaceIn(response: PlengiResponse) {
    }

    override fun onPlaceNear(response: PlengiResponse) {
    }
}