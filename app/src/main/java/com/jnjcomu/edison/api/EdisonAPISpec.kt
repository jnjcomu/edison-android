package com.jnjcomu.edison.api

import com.jnjcomu.edison.model.Session
import com.jnjcomu.edison.model.Ticket
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

interface EdisonAPISpec {
    //구버전
    /*@POST("/auth")
    fun login(@Field("id") id: String, @Field("pw") pw: String): Observable<Ticket>*/

    @POST("/auth/renew")
    fun fetchTicket(@Header("Authorization") ticket: Ticket): Observable<Ticket>

    @FormUrlEncoded
    @POST("/place/{id}/enter")
    fun enter(@Header("Authorization") ticket: Ticket, @Path("id") placeId: String): Call<Void>

    //신버전
    @GET("/edison/searchNo")
    fun searchNo(): Call<Void>

    @GET("/edison/inputLocation/1")
    fun checkin(@Query("data") data: String): Call<Void>

    @GET("/login/do")
    fun login(@Query("id") id: String, @Query("password") pw: String): Call<Void>

    @GET("/login/checklogin")
    fun checkLogin(): Call<Session>
}