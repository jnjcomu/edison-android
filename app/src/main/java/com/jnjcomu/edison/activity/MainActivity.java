package com.jnjcomu.edison.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jnjcomu.edison.EdisonApplication;
import com.jnjcomu.edison.R;
import com.jnjcomu.edison.callback.CloudEventListener;
import com.jnjcomu.edison.storage.Configuration;
import com.loplat.placeengine.PlengiResponse;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements CloudEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdisonApplication.getInstance().setEventListener(this);

        // get edison configuration file
        Configuration conf = new Configuration(this);
        String clientKey = conf.getClientKey();
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
