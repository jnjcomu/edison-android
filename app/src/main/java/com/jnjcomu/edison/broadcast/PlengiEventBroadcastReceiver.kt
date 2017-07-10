package com.jnjcomu.edison.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.jnjcomu.edison.addition.EdisonAPI
import com.jnjcomu.edison.addition.userStorage
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlengiEventBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != RECEIVER_ID) return

        context.toast(intent.getStringExtra("place.id"))
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
