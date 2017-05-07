package com.jnjcomu.edison.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jnjcomu.edison.EdisonApplication;
import com.jnjcomu.edison.R;
import com.jnjcomu.edison.callback.CloudEventListener;
import com.loplat.placeengine.PlengiResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements CloudEventListener {

    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    @ViewById
    protected TextView txtPlace;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EdisonApplication.getInstance().destroyEventListener();
    }

    @AfterViews
    protected void activitySetting() {
        setSupportActionBar(toolbar);
        EdisonApplication.getInstance().setEventListener(this);
    }

    @Override
    public void onPlaceDefault(PlengiResponse response) {

    }

    @Override
    public void onPlaceNear(PlengiResponse response) {

    }

    @Override
    public void onPlaceIn(PlengiResponse response) {
        txtPlace.setText(response.place.name);
    }
}
