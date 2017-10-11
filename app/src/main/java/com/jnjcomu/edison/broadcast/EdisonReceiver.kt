package com.jnjcomu.edison.broadcast

import android.app.PendingIntent
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.jnjcomu.edison.R
import com.jnjcomu.edison.activity.SplashActivity
import com.jnjcomu.edison.api.API
import com.jnjcomu.edison.callback.ApiListener
import com.jnjcomu.edison.callback.CloudEventListener
import com.jnjcomu.edison.model.SearchNum
import com.jnjcomu.edison.network.NetManager
import com.jnjcomu.edison.storage.AppSettingStorage
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

        val nm = NetManager(context)

        if(AppSettingStorage(context).isActiveScanning) {
            if(!nm.isConnected()) {
                noti("네트워크에 연결되지 않아 위치 전송에 실패했습니다.")
            } else {
                if(!nm.isActive())
                    nm.activeWifi()
                API.setListener(this)
                Plengi.getInstance(context).refreshPlace()
            }
        }
    }

    override fun onPlaceDefault(response: PlengiResponse) {
        if(response.place.name == null) {
            noti("등록되지 않은 장소에 있어 위치 전송에 실패했습니다.")
        } else {
            val call = API.getApi(context!!).searchNo()

            call.enqueue(object : Callback<SearchNum> {
                override fun onResponse(call: Call<SearchNum>?, repo: Response<SearchNum>?) {
                    API.checkIn(context!!, repo!!.body()!!.num!! + 1, response.place.name)
                }

                override fun onFailure(call: Call<SearchNum>?, t: Throwable?) {

                }
            })
        }
    }

    override fun onPlaceIn(response: PlengiResponse) {
    }

    override fun onPlaceNear(response: PlengiResponse) {
    }

    override fun onResponse(response: Response<Void>?) {
        noti("위치 정보가 전송되었습니다.")
    }

    override fun onFailure() {

    }

    fun noti(msg: String) {
        val builder = NotificationCompat.Builder(context)
                .setContentTitle("Edison")
                .setContentText(msg)
                .setSmallIcon(R.drawable.logo_white)
        val notifyIntent = Intent(context, SplashActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        val notificationCompat = builder.build()
        val managerCompat = NotificationManagerCompat.from(context)
        managerCompat.notify(777, notificationCompat)
    }
}