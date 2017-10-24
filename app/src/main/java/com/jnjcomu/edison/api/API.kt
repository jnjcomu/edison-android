package com.jnjcomu.edison.api

import android.content.Context
import com.jnjcomu.edison.callback.ApiListener
import com.jnjcomu.edison.interceptor.AddCookiesInterceptor
import com.jnjcomu.edison.interceptor.ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-09-29
 */
object API : Callback<Void> {

    var apiListener: ApiListener? = null

    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
        apiListener?.onResponse(response)
    }

    override fun onFailure(call: Call<Void>?, t: Throwable?){
        apiListener?.onFailure()
    }

    fun getApi(context: Context): EdisonAPISpec {
        val client = OkHttpClient.Builder()
                .addInterceptor(AddCookiesInterceptor(context))
                .addInterceptor(ReceivedCookiesInterceptor(context))
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(APIScheme.SERVER_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(EdisonAPISpec::class.java)

        return retrofit
    }
}