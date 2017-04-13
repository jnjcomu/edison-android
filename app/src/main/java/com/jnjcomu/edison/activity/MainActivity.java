package com.jnjcomu.edison.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jnjcomu.edison.EdisonApplication;
import com.jnjcomu.edison.R;
import com.jnjcomu.edison.callback.CloudEventListener;
import com.jnjcomu.edison.module.Encrypt;
import com.jnjcomu.edison.module.LoadJson;
import com.loplat.placeengine.PlengiResponse;

public class MainActivity extends AppCompatActivity implements CloudEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdisonApplication.getInstance().setEventListener(this);

        //Edison.json 불러오기
        String[] s = LoadJson.loadloplat(getApplicationContext());
        Log.d("id", s[0]);
        Log.d("pw", s[1]);

        //암호화 코드
        /*byte b1[] = null, b2[] = null;
        try{
            b1 = s[0].getBytes("utf-8");
            b2 = s[1].getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("id_d", Encrypt.encrypt(getApplicationContext(), b1));
        Log.d("pw_d", Encrypt.encrypt(getApplicationContext(), b2));*/

        //복호화 코드
        Log.d("id_e", Encrypt.decrypt(getApplicationContext(), s[0]));
        Log.d("id_d", Encrypt.decrypt(getApplicationContext(), s[1]));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EdisonApplication.getInstance().setEventListener(null);
    }

    @Override
    public void onPlaceDefault(PlengiResponse response) {

    }

    @Override
    public void onPlaceNear(PlengiResponse response) {

    }

    @Override
    public void onPlaceIn(PlengiResponse response) {

    }
}
