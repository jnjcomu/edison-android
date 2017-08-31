package com.jnjcomu.edison.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jnjcomu.edison.addition.EdisonAPI
import com.jnjcomu.edison.addition.userStorage
import com.loplat.placeengine.PlengiResponse
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlengiEventBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != RECEIVER_ID) return

        val enterType = intent.getIntExtra("response.enterType", 0)
        if (enterType == PlengiResponse.EnterType.ENTER)
            context.toast("${intent.getStringExtra("place.name")}에 입실했습니다.")
        else if (enterType == PlengiResponse.EnterType.NEARBY)
            context.toast("${intent.getStringExtra("place.name")} 주변에 있습니다.")

        noticeRegion(context, intent, true)
    }

    private fun noticeRegion(context: Context, intent: Intent, retry: Boolean) {
        EdisonAPI.enter(
                context.userStorage.ticket!!,
                intent.getStringExtra("place.id")
        ).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (!response.isSuccessful) {
                    EdisonAPI
                            .fetchTicket(context.userStorage.ticket!!)
                            .subscribe { ticket -> context.userStorage.saveUserTicket(ticket) }

                    if (retry) noticeRegion(context, intent, false)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {}

        })
    }

    companion object {
        val RECEIVER_ID = "com.jnjcomu.edison.cloud.response"
    }
}
