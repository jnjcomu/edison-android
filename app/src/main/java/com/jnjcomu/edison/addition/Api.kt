package com.jnjcomu.edison.addition

import com.jnjcomu.edison.api.APIScheme
import com.jnjcomu.edison.api.EdisonAPISpec
import retrofit2.Retrofit

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-07-10
 */

val EdisonAPI: EdisonAPISpec = Retrofit.Builder()
        .baseUrl(APIScheme.SERVER_ENDPOINT)
        .build()
        .create(EdisonAPISpec::class.java)