package com.jnjcomu.edison.callback;

import com.loplat.placeengine.PlengiResponse;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-12
 */

public interface CloudEventListener {

    /**
     * calling when loplat response type 'PLACE'
     *
     * @param response PlengiResponse
     */
    void onPlaceDefault(PlengiResponse response);

    /**
     * calling when loplat response type 'NEAR'
     *
     * @param response PlengiResponse
     */
    void onPlaceNear(PlengiResponse response);

    /**
     * calling when loplat response type 'ENTER'
     *
     * @param response PlengiResponse
     */
    void onPlaceIn(PlengiResponse response);
}
