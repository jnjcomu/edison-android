package com.jnjcomu.edison.api

import com.jnjcomu.edison.model.Session
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-23
 */

interface EdisonAPISpec {

    @GET("/edison/inputLocation/{num}")
    fun checkin(@Path("num") num: Int, @Query("location") data: String): Call<Void>

    @GET("/send/do")
    fun login(@Query("id") id: String, @Query("password") pw: String): Call<Session>

    @GET("/send/checklogin")
    fun checkLogin(): Call<Session>

}