package com.jnjcomu.edison.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnjcomu.edison.EdisonApplication;
import com.jnjcomu.edison.R;
import com.jnjcomu.edison.ui.Anim;
import com.jnjcomu.edison.ui.LogoInterpolator;
import com.jnjcomu.edison.callback.CloudEventListener;
import com.loplat.placeengine.PlengiResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Timer;
import java.util.TimerTask;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements CloudEventListener {

    protected Anim anim = new Anim();
    protected LogoInterpolator li = new LogoInterpolator(0.2, 20);
    TimerTask mTask;
    Timer mTimer;

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected TextView txtPlace;

    @App
    protected EdisonApplication application;

    @ViewById
    protected ImageView logo;

    @ViewById(R.id.btn_retry)
    protected CardView retry;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        application.destroyEventListener();
        mTimer.cancel();
    }

    @AfterViews
    protected void initActivity() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        application.setEventListener(this);
        //application.getPlengi().start();
        timer();
        anim.startAnim(this, logo, R.anim.logo_vibrate);
    }

    @CheckedChange
    protected void swtScanning(boolean isChecked) {
        if(isChecked) {
            application.getPlengi().start();
            anim.startAnim(this, logo, R.anim.logo_vibrate);
        } else {
            application.getPlengi().stop();
            anim.cancelAnim(logo);
        }
    }

    @Click
    protected void btnRetry() {
        retry.setVisibility(View.GONE);
        //application.getPlengi().start();
        txtPlace.setText("스캔중...");
        anim.startAnim(this, logo, R.anim.logo_vibrate);
        timer();
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

    private void timer() {
        final Handler mHandler = new Handler();
        mTask = new TimerTask() {
            @Override
            public void run() {
                //application.getPlengi().stop();
                mHandler.post(() -> {
                    display("요청시간이 초과되었습니다.");
                    retry.setVisibility(View.VISIBLE);
                });
            }
        };

        mTimer = new Timer();
        mTimer.schedule(mTask, 30000);
    }

}
