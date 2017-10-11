package com.jnjcomu.edison.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jnjcomu.edison.R
import com.jnjcomu.edison.addition.appStorage
import com.jnjcomu.edison.api.API
import com.jnjcomu.edison.broadcast.BootBroadcastReceiver
import com.jnjcomu.edison.callback.ApiListener
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener, ApiListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        API.setListener(this)
    }

    override fun onClick(v: View) {
        API.login(this, edt_id_field.text.toString(), edt_pw_field.text.toString())
    }

    override fun onResponse(response: Response<Void>?) {
        if(appStorage.isFirstRun)
            firstRun()
        else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onFailure() {
    }

    fun firstRun() {
        sendBroadcast(Intent(this, BootBroadcastReceiver::class.java))
        startActivity(Intent(this, IntroActivity::class.java))
        finish()

        appStorage.saveFirstrun(false)
    }
}
