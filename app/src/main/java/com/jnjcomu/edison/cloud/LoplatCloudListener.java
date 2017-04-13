package com.jnjcomu.edison.cloud;

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
    }

    public void setListener(CloudEventListener cloudEventListener) {
        this.cloudEventListener = cloudEventListener;
    }
}
