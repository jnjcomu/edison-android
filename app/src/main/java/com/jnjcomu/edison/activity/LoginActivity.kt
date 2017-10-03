package com.jnjcomu.edison.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jnjcomu.edison.R
import com.jnjcomu.edison.api.API
import com.jnjcomu.edison.api.APIScheme
import com.jnjcomu.edison.api.EdisonAPISpec
import com.jnjcomu.edison.callback.ApiListener
import com.jnjcomu.edison.interceptor.AddCookiesInterceptor
import com.jnjcomu.edison.interceptor.ReceivedCookiesInterceptor
import com.jnjcomu.edison.model.Session
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity(), View.OnClickListener, ApiListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        API.setListener(this)

        session()
    }

    override fun onClick(v: View) {
        API.login(this, edt_id_field.text.toString(), edt_pw_field.text.toString())
    }

    override fun onResponse(response: Response<Void>?) {
        startActivity<MainActivity>()
    }

    override fun onFailure() {
    }

    //세션 확인 테스트용
    fun session() {
        val client = OkHttpClient.Builder()
                .addInterceptor(AddCookiesInterceptor(this))
                .addInterceptor(ReceivedCookiesInterceptor(this))
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(APIScheme.SERVER_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(EdisonAPISpec::class.java)

        val call = retrofit.checkLogin()

        call.enqueue(object : Callback<Session> {
            override fun onResponse(call: Call<Session>?, response: Response<Session>?) {
                if(response!!.body()!!.login.equals("true"))
                    startActivity<MainActivity>()
            }

            override fun onFailure(call: Call<Session>?, t: Throwable?) {

            }
        })
    }
}
