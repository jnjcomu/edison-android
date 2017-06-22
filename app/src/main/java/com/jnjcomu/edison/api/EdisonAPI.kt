package com.jnjcomu.edison.api

import com.jnjcomu.edison.model.Region
import com.jnjcomu.edison.model.Ticket

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author kimwoojae <wj1187></wj1187>@naver.com>
 * *
 * @since 2017-04-12
 */

interface EdisonAPI {
    @POST("/auth")
    fun login(@Field("id") id: String, @Field("pw") pw: String): Call<Ticket>

    @POST("/auth/renew")
    fun fetchTicket(@Header("Authorization") ticket: Ticket): Observable<Ticket>

    @FormUrlEncoded
    @POST("/place/{id}/enter")
    fun enter(@Header("Authorization") ticket: Ticket, @Path("id") placeId: String): Call<Void>
}