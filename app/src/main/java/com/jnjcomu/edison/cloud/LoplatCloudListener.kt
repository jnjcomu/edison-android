package com.jnjcomu.edison.cloud

import android.content.Context

import com.jnjcomu.edison.callback.CloudEventListener
import com.loplat.placeengine.PlengiListener
import com.loplat.placeengine.PlengiResponse

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-12
 */

class LoplatCloudListener(val context: Context) : PlengiListener {
    private var cloudEventListener: CloudEventListener? = null

    override fun listen(plengiResponse: PlengiResponse) {
        cloudEventListener?.let {
            try {
                when (plengiResponse.type) {
                    PlengiResponse.ResponseType.PLACE -> it.onPlaceDefault(plengiResponse)
                    PlengiResponse.ResponseType.PLACE_EVENT -> {
                        if (plengiResponse.placeEvent == PlengiResponse.PlaceEvent.ENTER) {
                            if (plengiResponse.enterType == PlengiResponse.EnterType.ENTER)
                                it.onPlaceIn(plengiResponse)
                            else if (plengiResponse.enterType == PlengiResponse.EnterType.NEARBY)
                                it.onPlaceNear(plengiResponse)
                        }
                    }
                }
            } catch (ignored: Exception) {
            }
        }
    }

    fun setListener(cloudEventListener: CloudEventListener?) {
        this.cloudEventListener = cloudEventListener
    }

}
