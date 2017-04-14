package com.jnjcomu.edison.cloud;

import android.content.Intent;

import com.jnjcomu.edison.EdisonApplication;
import com.jnjcomu.edison.broadcast.PlengiEventBroadcastReceiver;
import com.jnjcomu.edison.callback.CloudEventListener;
import com.loplat.placeengine.PlengiListener;
import com.loplat.placeengine.PlengiResponse;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-12
 */

public class LoplatCloudListener implements PlengiListener {
    private CloudEventListener cloudEventListener;

    @Override
    public void listen(PlengiResponse plengiResponse) {
        if (cloudEventListener == null) return; // Null check for cloudEventListener

        switch (plengiResponse.type) {
            case PlengiResponse.ResponseType.PLACE:
                cloudEventListener.onPlaceDefault(plengiResponse);
                break;
            case PlengiResponse.ResponseType.PLACE_EVENT:
                if (plengiResponse.placeEvent == PlengiResponse.PlaceEvent.ENTER) {
                    if (plengiResponse.enterType == PlengiResponse.EnterType.ENTER)
                        cloudEventListener.onPlaceIn(plengiResponse);
                    else if (plengiResponse.enterType == PlengiResponse.EnterType.NEARBY)
                        cloudEventListener.onPlaceNear(plengiResponse);
                }
                break;
        }

        sendBroadcast(plengiResponse);
    }

    public void setListener(CloudEventListener cloudEventListener) {
        this.cloudEventListener = cloudEventListener;
    }

    private void sendBroadcast(PlengiResponse response) {
        Intent broadcastInfo = new Intent(PlengiEventBroadcastReceiver.RECEIVER_ID);

        broadcastInfo.putExtra("place.name", response.place.name);
        broadcastInfo.putExtra("place.floor", response.place.floor);

        broadcastInfo.putExtra("response.result", response.result);
        broadcastInfo.putExtra("response.enterType", response.enterType);
        broadcastInfo.putExtra("response.type", response.type);
        broadcastInfo.putExtra("response.placeEvent", response.placeEvent);

        EdisonApplication.getInstance().sendBroadcast(broadcastInfo);
    }
}
