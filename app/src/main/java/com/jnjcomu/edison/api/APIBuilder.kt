package com.jnjcomu.edison.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author CodeRi13 <ruto1924></ruto1924>@gmail.com>
 * *
 * @since 2017-04-14
 */

object APIBuilder {
    private val SERVER_ENDPOINT_PROTOCOL = "http"
    private val SERVER_ENDPOINT_PORT = "80"
    private val SERVER_ENDPOINT_URL = "dev.jnj.club"
    private val SERVER_ENDPOINT =
            "$SERVER_ENDPOINT_PROTOCOL://$SERVER_ENDPOINT_URL:$SERVER_ENDPOINT_PORT"

    val api: EdisonAPI
        get() {
            val retrofit = Retrofit.Builder()
                    .baseUrl(SERVER_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(EdisonAPI::class.java)
        }
}
