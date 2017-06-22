package com.jnjcomu.edison.cloud

import android.content.Intent

import com.jnjcomu.edison.EdisonApplication
import com.jnjcomu.edison.broadcast.PlengiEventBroadcastReceiver
import com.jnjcomu.edison.callback.CloudEventListener
import com.loplat.placeengine.PlengiListener
import com.loplat.placeengine.PlengiResponse

/**
 * @author CodeRi13 <ruto1924></ruto1924>@gmail.com>
 * *
 * @since 2017-04-12
 */

class LoplatCloudListener : PlengiListener {
    private var cloudEventListener: CloudEventListener? = null

    override fun listen(plengiResponse: PlengiResponse) {
        if (cloudEventListener == null) return  // Null check for cloudEventListener
        try {
            when (plengiResponse.type) {
                PlengiResponse.ResponseType.PLACE -> cloudEventListener!!.onPlaceDefault(plengiResponse)
                PlengiResponse.ResponseType.PLACE_EVENT -> {
                    if (plengiResponse.placeEvent == PlengiResponse.PlaceEvent.ENTER) {
                        if (plengiResponse.enterType == PlengiResponse.EnterType.ENTER)
                            cloudEventListener!!.onPlaceIn(plengiResponse)
                        else if (plengiResponse.enterType == PlengiResponse.EnterType.NEARBY)
                            cloudEventListener!!.onPlaceNear(plengiResponse)
                    }
                    sendBroadcast(plengiResponse)
                }
            }
        } catch (ignored: Exception) {
        }

    }

    fun setListener(cloudEventListener: CloudEventListener) {
        this.cloudEventListener = cloudEventListener
    }

    private fun sendBroadcast(response: PlengiResponse) {
        val broadcastInfo = Intent(PlengiEventBroadcastReceiver.RECEIVER_ID)

        broadcastInfo.putExtra("place.id", response.place.loplatid)
        broadcastInfo.putExtra("place.name", response.place.name)
        broadcastInfo.putExtra("place.floor", response.place.floor)

        broadcastInfo.putExtra("response.result", response.result)
        broadcastInfo.putExtra("response.enterType", response.enterType)
        broadcastInfo.putExtra("response.type", response.type)
        broadcastInfo.putExtra("response.placeEvent", response.placeEvent)

        EdisonApplication.instance!!.sendBroadcast(broadcastInfo)
    }
}
