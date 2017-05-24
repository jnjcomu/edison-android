package com.jnjcomu.edison.api;

import com.jnjcomu.edison.model.Region;
import com.jnjcomu.edison.model.Ticket;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

public interface EdisonAPI {
    @POST("/auth")
    Call<Ticket> login(@Field("id") String id, @Field("pw") String pw);

    @POST("/auth/renew")
    Call<Ticket> fetchTicket(@Header("ticket") Ticket ticket);

    @FormUrlEncoded
    @POST("/place/notice")
    Call<Void> noticeRegion(@Header("ticket") Ticket ticket, @Body Region region);
}