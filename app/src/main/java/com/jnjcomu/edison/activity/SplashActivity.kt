package com.jnjcomu.edison.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.jnjcomu.edison.R
import com.jnjcomu.edison.addition.netManager
import com.jnjcomu.edison.api.API
import com.jnjcomu.edison.model.Session
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        if(!netManager.isConnected()) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("안내")
            builder.setMessage("네트워크 연결을 확인해주세요.")
            builder.setCancelable(false)
            builder.setPositiveButton("확인", { _, _ ->
                finish()
            })
            builder.show()
        } else {
            val call = API.getApi(this).checkLogin()

            call.enqueue(object : Callback<Session> {
                override fun onResponse(call: Call<Session>?, response: Response<Session>?) {
                    try {
                        if (response?.body()?.login.equals("true")) {
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            finish()
                        } else {
                            startActivity(Intent(applicationContext, LoginActivity::class.java))
                            finish()
                        }
                    } catch (e: NullPointerException) {
                        startActivity(Intent(applicationContext, LoginActivity::class.java))
                    }
                }

                override fun onFailure(call: Call<Session>?, t: Throwable?) {

                }
            })
        }
    }

    override fun onAnimationRepeat(animation: Animation) {

    }
}
