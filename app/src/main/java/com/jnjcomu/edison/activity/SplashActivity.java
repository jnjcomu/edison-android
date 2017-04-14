package com.jnjcomu.edison.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.jnjcomu.edison.R;

public class SplashActivity extends AppCompatActivity {
    private ImageView imgLogo;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgLogo = (ImageView) findViewById(R.id.img_logo);

        handler = new Handler();
        handler.postDelayed(() -> imgLogo.startAnimation(getReadyAnimation()), 2000);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_simple_in, R.anim.activity_simple_out);
    }

    private Animation getReadyAnimation() {
        final Animation ani = AnimationUtils.loadAnimation(this, R.anim.dynamic_logo);

        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return ani;
    }
}
