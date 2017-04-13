package com.jnjcomu.edison.callback;

import com.loplat.placeengine.PlengiResponse;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-12
 */

public interface CloudEventListener {
    void onPlaceDefault(PlengiResponse response);
    void onPlaceNear(PlengiResponse response);
    void onPlaceIn(PlengiResponse response);
}
