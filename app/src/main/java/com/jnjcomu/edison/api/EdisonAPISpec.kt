package com.jnjcomu.edison.api

import com.jnjcomu.edison.model.Ticket
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

interface EdisonAPISpec {
    @POST("/auth")
    fun login(@Field("id") id: String, @Field("pw") pw: String): Observable<Ticket>

    @POST("/auth/renew")
    fun fetchTicket(@Header("Authorization") ticket: Ticket): Observable<Ticket>

    @FormUrlEncoded
    @POST("/place/{id}/enter")
    fun enter(@Header("Authorization") ticket: Ticket, @Path("id") placeId: String): Call<Void>

    @GET(" ")
    fun checkin(@Query("data") data: String): Call<Void>
}