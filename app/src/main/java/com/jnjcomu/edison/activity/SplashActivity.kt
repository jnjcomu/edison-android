package com.jnjcomu.edison.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.widget.ImageView

import com.jnjcomu.edison.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), Animation.AnimationListener {

    internal var imgLogo: ImageView = img_logo

    //TODO 이거 해결
    internal var dynamicLogo: Animation = R.anim.dynamic_logo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({ imgLogo.startAnimation(readyAnimation) }, 1000)
    }

    override fun onBackPressed() {

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_simple_in, R.anim.activity_simple_out)
    }

    private val readyAnimation: Animation
        get() {
            dynamicLogo.setAnimationListener(this)
            return dynamicLogo
        }

    override fun onAnimationStart(animation: Animation) {

    }

    override fun onAnimationEnd(animation: Animation) {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }

    override fun onAnimationRepeat(animation: Animation) {

    }
}
