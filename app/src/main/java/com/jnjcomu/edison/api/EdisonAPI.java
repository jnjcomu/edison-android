package com.jnjcomu.edison.api;

import com.jnjcomu.edison.model.Region;
import com.jnjcomu.edison.model.Ticket;
import com.jnjcomu.edison.model.response.CheckTicketResponse;
import com.jnjcomu.edison.model.response.FetchTicketResponse;
import com.jnjcomu.edison.model.response.LoginResponse;
import com.jnjcomu.edison.model.response.NoticeRegionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

public interface EdisonAPI {
    @POST("/login")
    Call<LoginResponse> login(@Field("id") String id, @Field("pw") String pw);

    @POST("/ticket/new")
    Call<FetchTicketResponse> fetchTicket(@Header("ticket") Ticket ticket);

    @GET("/ticket/test")
    Call<CheckTicketResponse> checkTicket(@Header("ticket") Ticket ticket);

    @FormUrlEncoded
    @POST("/place/notice")
    Call<NoticeRegionResponse> noticeRegion(@Header("ticket") Ticket ticket, @Body Region region);
}