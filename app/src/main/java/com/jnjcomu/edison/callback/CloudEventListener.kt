package com.jnjcomu.edison.callback

import com.loplat.placeengine.PlengiResponse

/**
 * @author CodeRi13 <ruto1924></ruto1924>@gmail.com>
 * *
 * @since 2017-04-12
 */

interface CloudEventListener {

    /**
     * calling when loplat response type 'PLACE'

     * @param response PlengiResponse
     */
    fun onPlaceDefault(response: PlengiResponse)

    /**
     * calling when loplat response type 'NEAR'

     * @param response PlengiResponse
     */
    fun onPlaceNear(response: PlengiResponse)

    /**
     * calling when loplat response type 'ENTER'

     * @param response PlengiResponse
     */
    fun onPlaceIn(response: PlengiResponse)
}
