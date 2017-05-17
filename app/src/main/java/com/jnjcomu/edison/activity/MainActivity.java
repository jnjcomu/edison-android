package com.jnjcomu.edison.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jnjcomu.edison.EdisonApplication;
import com.jnjcomu.edison.R;
import com.jnjcomu.edison.callback.CloudEventListener;
import com.loplat.placeengine.PlengiResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements CloudEventListener {

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected TextView txtPlace;

    @App
    EdisonApplication application;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        application.destroyEventListener();
    }

    @AfterViews
    protected void initActivity() {
        setSupportActionBar(toolbar);
        application.setEventListener(this);
    }

    @CheckedChange
    public void swtScanning(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {

        } else {

        }
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
