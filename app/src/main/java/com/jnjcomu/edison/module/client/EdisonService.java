package com.jnjcomu.edison.module.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

public interface EdisonService {

    @GET("users/identify?username={username}&password={passwd}")
    Call<EdisonGson> getUser(@Path("username") String username, @Path("passwd") String passwd);

}