package com.jnjcomu.edison.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

import com.jnjcomu.edison.api.APIBuilder
import com.jnjcomu.edison.storage.UserStorage

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlengiEventBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != RECEIVER_ID) return
        Toast.makeText(context, intent.getStringExtra("place.id"), Toast.LENGTH_SHORT).show()

        noticeRegion(context, intent, true)
    }

    private fun noticeRegion(context: Context, intent: Intent, retry: Boolean) {
        val userStorage = UserStorage.getInstance(context)

        APIBuilder.api.enter(
                userStorage.ticket!!,
                intent.getStringExtra("place.id")
        ).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (!response.isSuccessful) {
                    APIBuilder.api
                            .fetchTicket(userStorage.ticket!!)
                            .subscribe({ ticket -> UserStorage.getInstance(context).saveUserTicket(ticket) })

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
