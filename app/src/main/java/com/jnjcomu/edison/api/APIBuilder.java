package com.jnjcomu.edison.api;

import android.support.annotation.NonNull;

import com.jnjcomu.edison.util.Schema;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-14
 */

public class APIBuilder {

    @NonNull
    public static EdisonAPI getAPI() {
        EdisonAPI api;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Schema.SERVER_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(EdisonAPI.class);

        return api;
    }
}
