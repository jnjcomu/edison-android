package com.jnjcomu.edison.api;

import com.jnjcomu.edison.model.response.CheckTicketResponse;
import com.jnjcomu.edison.model.response.LoginResponse;

import retrofit2.Call;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

public interface EdisonAPI {
    // TODO : Call 작성 (현재는 간단한 설계만)
    Call<LoginResponse> login();
    void fetchTicket();
    Call<CheckTicketResponse> checkTicket();
    void noticeRegion();
}