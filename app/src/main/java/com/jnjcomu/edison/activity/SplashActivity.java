package com.jnjcomu.edison.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.jnjcomu.edison.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.AnimationRes;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {

    @ViewById(R.id.img_logo)
    ImageView imgLogo;

    @AnimationRes
    Animation dynamicLogo;

    @Override
    public void onBackPressed() {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_simple_in, R.anim.activity_simple_out);
    }

    @AfterViews
    protected void ready() {
        Handler handler = new Handler();
        handler.postDelayed(() -> imgLogo.startAnimation(getReadyAnimation()), 1000);
    }

    @NonNull
    private Animation getReadyAnimation() {
        dynamicLogo.setAnimationListener(this);
        return dynamicLogo;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        finish();
        startActivity(new Intent(SplashActivity.this, MainActivity_.class));
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
