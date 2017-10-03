package com.jnjcomu.edison.interceptor

import android.content.Context
import com.jnjcomu.edison.storage.CookieStorage

import java.io.IOException
import java.util.HashSet

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author kimwoojae <wj1187></wj1187>@naver.com>
 * @since 2017-09-29
 */

class ReceivedCookiesInterceptor(context: Context) : Interceptor {

    var context: Context? = null

    init {
        this.context = context
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            val cookies = HashSet<String>()

            cookies.addAll(originalResponse.headers("Set-Cookie"))

            CookieStorage(context!!).putCookie(cookies)
        }
        return originalResponse
    }

}