package com.jnjcomu.edison.callback

import retrofit2.Response

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-09-29
 */
interface ApiListener {
    fun onResponse(response: Response<Void>?)

    fun onFailure()
}