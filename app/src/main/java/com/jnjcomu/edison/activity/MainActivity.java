package com.jnjcomu.edison.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnjcomu.edison.EdisonApplication;
import com.jnjcomu.edison.R;
import com.jnjcomu.edison.anim.Anim;
import com.jnjcomu.edison.anim.LogoInterpolator;
import com.jnjcomu.edison.callback.CloudEventListener;
import com.loplat.placeengine.PlengiResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements CloudEventListener {

    protected Anim anim = new Anim();
    protected LogoInterpolator li = new LogoInterpolator(0.2, 20);

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected TextView txtPlace;

    @App
    protected EdisonApplication application;

    @ViewById
    protected ImageView logo;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        application.destroyEventListener();
    }

    @AfterViews
    protected void initActivity() {
        setSupportActionBar(toolbar);
        application.setEventListener(this);
        anim.startAnim(this, logo, R.anim.logo_vibrate);
    }

    @CheckedChange
    protected void swtScanning(boolean isChecked) {
        if(isChecked) {
            application.setEventListener(MainActivity.this);
            anim.startAnim(this, logo, R.anim.logo_vibrate);
        } else {
            application.destroyEventListener();
            anim.cancelAnim(logo);
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
        String msg = "현재 " + response.place.name + " 주변 입니다.";
        display(msg);
    }

    /**
     * EventListener
     *
     * @param response PlengiResponse
     * @see CloudEventListener
     */
    @Override
    public void onPlaceIn(PlengiResponse response) {
        String msg = "현재 계신 장소는 " + response.place.name + "입니다.";
        display(msg);
    }

    /**
     *
     * @param msg String
     */
    private void display(String msg) {
        txtPlace.setText(msg);
        anim.cancelAnim(logo);
        anim.startAnim(this, logo, R.anim.logo_scale, li);
    }

}
