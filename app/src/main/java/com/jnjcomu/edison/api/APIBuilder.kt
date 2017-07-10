package com.jnjcomu.edison.api

import retrofit2.Retrofit

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-14
 */

object APIScheme {
    val SERVER_ENDPOINT_PROTOCOL = "http"
    val SERVER_ENDPOINT_PORT = "80"
    val SERVER_ENDPOINT_URL = "dev.jnj.club"
    val SERVER_ENDPOINT = "$SERVER_ENDPOINT_PROTOCOL://$SERVER_ENDPOINT_URL:$SERVER_ENDPOINT_PORT"
}

val EdisonAPI: EdisonAPISpec = Retrofit.Builder()
        .baseUrl(APIScheme.SERVER_ENDPOINT)
        .build()
        .create(EdisonAPISpec::class.java)