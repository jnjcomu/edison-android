package com.jnjcomu.edison.api;

import com.jnjcomu.edison.model.Region;
import com.jnjcomu.edison.model.Ticket;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

public interface EdisonAPI {
    @POST("/auth")
    Call<Ticket> login(@Field("id") String id, @Field("pw") String pw);

    @POST("/auth/renew")
    Observable<Ticket> fetchTicket(@Header("Authorization") Ticket ticket);

    @FormUrlEncoded
    @POST("/place/{id}/enter")
    Call<Void> enter(@Header("Authorization") Ticket ticket, @Path("id") String placeId);
}