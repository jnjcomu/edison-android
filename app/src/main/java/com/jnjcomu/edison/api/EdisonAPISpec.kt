package com.jnjcomu.edison.api

import com.jnjcomu.edison.model.Location
import com.jnjcomu.edison.model.Session
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

interface EdisonAPISpec {

    @FormUrlEncoded
    @POST("/edison/inputLocation")
    fun checkIn(@Field("location") data: String): Call<Void>

    @FormUrlEncoded
    @POST("/login/api")
    fun login(@Field("id") id: String, @Field("password") pw: String): Call<Session>

    @GET("/login/checklogin")
    fun checkLogin(): Call<Session>

    @GET("/edison/locationList")
    fun getList(): Call<List<Location>>

}