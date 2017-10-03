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
import com.jnjcomu.edison.api.API
import com.jnjcomu.edison.callback.ApiListener
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

class EdisonReceiver : BroadcastReceiver(), CloudEventListener, ApiListener {

    var context: Context? = null

    override fun onReceive(context: Context, intent: Intent) {
        this.context = context
        API.setListener(this)
        Plengi.getInstance(context).refreshPlace()
    }

    override fun onPlaceDefault(response: PlengiResponse) {
        API.checkIn(context!!, response.place.name)
    }

    override fun onPlaceIn(response: PlengiResponse) {
    }

    override fun onPlaceNear(response: PlengiResponse) {
    }

    override fun onResponse(response: Response<Void>?) {
        val builder = NotificationCompat.Builder(context)
            .setContentTitle("Edison")
            .setContentText("현재 위치를 전송했습니다.")
            .setSmallIcon(R.mipmap.ic_launcher)
        val notifyIntent = Intent(context, SplashActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        val notificationCompat = builder.build()
        val managerCompat = NotificationManagerCompat.from(context)
        managerCompat.notify(777, notificationCompat)
    }

    override fun onFailure() {

    }
}