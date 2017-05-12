package com.jnjcomu.edison.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnjcomu.edison.EdisonApplication;
import com.jnjcomu.edison.R;
import com.jnjcomu.edison.anim.Anim;
import com.jnjcomu.edison.anim.LogoInterpolator;
import com.jnjcomu.edison.callback.CloudEventListener;
import com.loplat.placeengine.PlengiResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements CloudEventListener {

    Anim anim = new Anim();
    LogoInterpolator li = new LogoInterpolator(0.15, 18);

    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    @ViewById
    protected TextView txtPlace;

    @ViewById
    protected ImageView logo;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EdisonApplication.getInstance().destroyEventListener();
    }

    @AfterViews
    protected void activitySetting() {
        setSupportActionBar(toolbar);
        EdisonApplication.getInstance().setEventListener(this);
        anim.startAnim(this, logo, R.anim.logo_vibrate);
    }

    @CheckedChange(R.id.swt_scanning)
    protected void onCheckedChange(boolean isChecked) {
        if(isChecked) {
            EdisonApplication.getInstance().setEventListener(MainActivity.this);
            anim.startAnim(this, logo, R.anim.logo_vibrate);
        } else {
            EdisonApplication.getInstance().destroyEventListener();
            anim.cancelAnim(logo);
            anim.startAnim(this, logo, R.anim.logo_scale, li);
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
        txtPlace.setText(msg);
        anim.cancelAnim(logo);
        anim.startAnim(this, logo, R.anim.logo_scale, li);
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
        txtPlace.setText(msg);
        anim.cancelAnim(logo);
        anim.startAnim(this, logo, R.anim.logo_scale, li);
    }

}
