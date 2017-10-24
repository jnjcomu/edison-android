package com.jnjcomu.edison.interceptor

import android.content.Context
import com.jnjcomu.edison.storage.CookieStorage
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author kimwoojae <wj1187></wj1187>@naver.com>
 * @since 2017-09-29
 */

class AddCookiesInterceptor(context: Context) : Interceptor {

    var context: Context? = null

    init {
        this.context = context
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val preferences = CookieStorage(context!!).getCookie()

        if(preferences!=null)
            for (cookie in preferences) {
                builder.addHeader("Cookie", cookie)
            }

        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android")
        return chain.proceed(builder.build())
    }

}