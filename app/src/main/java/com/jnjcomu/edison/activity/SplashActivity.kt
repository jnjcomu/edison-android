package com.jnjcomu.edison.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.jnjcomu.edison.R
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity(), Animation.AnimationListener {
    private val dynamicLogo: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.dynamic_logo)
    }

    private val readyAnimation: Animation
        get() {
            dynamicLogo.setAnimationListener(this)

            return dynamicLogo
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({ img_logo.startAnimation(readyAnimation) }, 1000)
    }

    override fun onBackPressed() {

    }

    override fun finish() {
        super.finish()

        overridePendingTransition(R.anim.activity_simple_in, R.anim.activity_simple_out)
    }

    override fun onAnimationStart(animation: Animation) {

    }

    override fun onAnimationEnd(animation: Animation) {
        startActivity<MainActivity>()

        finish()
    }

    override fun onAnimationRepeat(animation: Animation) {

    }
}
