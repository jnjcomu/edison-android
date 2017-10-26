package com.jnjcomu.edison.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.jnjcomu.edison.R
import com.jnjcomu.edison.addition.appStorage
import com.jnjcomu.edison.api.API
import com.jnjcomu.edison.broadcast.BootBroadcastReceiver
import com.jnjcomu.edison.model.Session
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onClick(v: View) {
        progress_bar.visibility = View.VISIBLE
        progress_bar.isIndeterminate = true

        val call = API.getApi(this).login(edt_id_field.text.toString(), edt_pw_field.text.toString())

        call.enqueue(object : Callback<Session> {
            override fun onResponse(call: Call<Session>?, response: Response<Session>?) {
                progress_bar.visibility = View.INVISIBLE
                try {
                    if (response?.body()?.login.equals("true")) {
                        appStorage.saveUserName(response!!.body()!!.name)
                        if(appStorage.isFirstRun)
                            firstRun()
                        else {
                            startActivity(Intent(applicationContext, CheckInActivity::class.java))
                            finish()
                        }
                    } else {
                        showToast("잘못된 아이디나 비밀번호입니다.")
                    }
                } catch (e: NullPointerException) {
                    showToast("잘못된 아이디나 비밀번호입니다.")
                }
            }

            override fun onFailure(call: Call<Session>?, t: Throwable?) {
                progress_bar.visibility = View.INVISIBLE
                showToast("잘못된 아이디나 비밀번호입니다.")
            }
        })
    }

    fun firstRun() {
        sendBroadcast(Intent(this, BootBroadcastReceiver::class.java))
        startActivity(Intent(this, IntroActivity::class.java))
        finish()
    }

    fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}
