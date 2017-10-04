package com.jnjcomu.edison.api

import com.jnjcomu.edison.model.SearchNum
import com.jnjcomu.edison.model.Session
import retrofit2.Call
import retrofit2.http.*

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

interface EdisonAPISpec {

    @GET("/edison/searchNo")
    fun searchNo(): Call<SearchNum>

    @GET("/edison/inputLocation/{num}")
    fun checkin(@Path("num") num: Int, @Query("location") data: String): Call<Void>

    @GET("/login/do")
    fun login(@Query("id") id: String, @Query("password") pw: String): Call<Void>

    @GET("/login/checklogin")
    fun checkLogin(): Call<Session>

}