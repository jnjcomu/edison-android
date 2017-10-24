package com.jnjcomu.edison.api

import com.jnjcomu.edison.model.Session
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

interface EdisonAPISpec {

    @GET("/edison/inputLocation")
    fun checkin(@Query("location") data: String): Call<Void>

    @GET("/login/do")
    fun login(@Query("id") id: String, @Query("password") pw: String): Call<Session>

    @GET("/login/checklogin")
    fun checkLogin(): Call<Session>

}