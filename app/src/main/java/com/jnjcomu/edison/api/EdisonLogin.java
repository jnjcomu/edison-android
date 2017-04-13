package com.jnjcomu.edison.api;

import com.jnjcomu.edison.model.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

public interface EdisonLogin {

    @GET("users/identify?username={username}&password={passwd}")
    Call<Login> getUser(@Path("username") String username, @Path("passwd") String passwd);

}