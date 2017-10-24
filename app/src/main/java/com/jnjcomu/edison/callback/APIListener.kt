package com.jnjcomu.edison.callback

import retrofit2.Response

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-23
 */
interface ApiListener {
    fun onResponse(response: Response<Void>?)

    fun onFailure()
}