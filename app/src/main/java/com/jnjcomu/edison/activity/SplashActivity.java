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

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.AnimationRes;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity {

    @ViewById(R.id.img_logo)
    ImageView imgLogo;

    @AnimationRes
    Animation dynamicLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(() -> imgLogo.startAnimation(getReadyAnimation()), 2000);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_simple_in, R.anim.activity_simple_out);
    }

    @NonNull
    private Animation getReadyAnimation() {
        dynamicLogo.setAnimationListener(new Animation.AnimationListener() {
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
        });

        return dynamicLogo;
    }
}
