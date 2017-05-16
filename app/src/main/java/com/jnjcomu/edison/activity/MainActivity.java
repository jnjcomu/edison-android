package com.jnjcomu.edison.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jnjcomu.edison.EdisonApplication;
import com.jnjcomu.edison.R;
import com.jnjcomu.edison.callback.CloudEventListener;
import com.jnjcomu.edison.encrypt.Encrypter;
import com.jnjcomu.edison.storage.Configuration;
import com.loplat.placeengine.PlengiResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.crypto.SecretKey;

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

    /**
     * EventListener
     *
     * @param response PlengiResponse
     * @see CloudEventListener
     */
    @Override
    public void onPlaceDefault(PlengiResponse response) {

    }

    /**
     * EventListener
     *
     * @param response PlengiResponse
     * @see CloudEventListener
     */
    @Override
    public void onPlaceNear(PlengiResponse response) {

    }

    /**
     * EventListener
     *
     * @param response PlengiResponse
     * @see CloudEventListener
     */
    @Override
    public void onPlaceIn(PlengiResponse response) {
        txtPlace.setText(response.place.name);
    }
}
