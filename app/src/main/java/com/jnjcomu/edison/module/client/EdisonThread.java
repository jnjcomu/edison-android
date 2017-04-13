package com.jnjcomu.edison.module.client;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

public class EdisonThread extends Thread {

    final static String TAG = "WeatherThread";
    private Context mContext;
    private EdisonGson mEdison;
    private Handler handler;

    private String userid;
    private String passwd;

    public EdisonThread(Handler handler, Context mContext, String userid, String passwd) {
        this.mContext = mContext;
        this.userid = String. valueOf(userid);
        this.passwd = String .valueOf(passwd);
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        Retrofit client = new Retrofit.Builder().baseUrl("http://api.dimigo.org/v1/").addConverterFactory(GsonConverterFactory.create()).build();
        EdisonService service = client.create(EdisonService.class);
        Call<EdisonGson> call = service.getUser(userid, passwd);
        call.enqueue(new Callback<EdisonGson>() {
            @Override
            public void onResponse(Call<EdisonGson> call, Response<EdisonGson> response) {
                if(response.isSuccessful()){
                    mEdison = response.body();
                    String usertype = mEdison.getUsertype();
                    switch (usertype) {
                        case "S" :
                            break;
                        case "T" :
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<EdisonGson> call, Throwable t) {
                Log.e(TAG, t.getMessage() );
                Log.e(TAG, call.request().toString());
            }
        });
    }
}