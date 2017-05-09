package com.jnjcomu.edison.api;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-14
 */

public class APIBuilder {
    private static final String SERVER_ENDPOINT_PROTOCOL = "http";
    private static final String SERVER_ENDPOINT_PORT = "80";
    private static final String SERVER_ENDPOINT_URL = "dev.jnj.club";
    private static final String SERVER_ENDPOINT =
            SERVER_ENDPOINT_PROTOCOL + "://" + SERVER_ENDPOINT_URL + ":" + SERVER_ENDPOINT_PORT;

    @NonNull
    public static EdisonAPI getAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EdisonAPI.class);
    }
}
