package com.jnjcomu.edison.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jnjcomu.edison.R
import com.jnjcomu.edison.api.EdisonAPI
import com.jnjcomu.edison.factory.UserFactory
import com.jnjcomu.edison.model.Ticket
import com.jnjcomu.edison.storage.userStorage
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
 * NOTE
 * ProgressDialog 가 Progress 작업은 엑티비티 뷰 안에서 이루어
 * 져야 한다는 Google Material Design Guideline 에 따라 지원 중단됨.
 */
class LoginActivity : AppCompatActivity(), Callback<Ticket> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val userId = edt_id_field.text.toString()
            val userPassword = edt_pw_field.text.toString()

            // TODO Add login logic
            // EdisonAPI.login(userId, userPassword).enqueue(this)

            sendResult(true)
        }
    }

    private fun sendResult(finishLogin: Boolean, message: String) {
        progress_bar.visibility = View.GONE

        if (finishLogin) {
            startActivity<MainActivity>()
            finish()
        }

        if ("" != message) {
            AlertDialog.Builder(this)
                    .setMessage(message)
                    .show()
        }
    }

    private fun sendResult(finishLogin: Boolean) {
        sendResult(finishLogin, "")
    }

    override fun onResponse(call: Call<Ticket>, response: Response<Ticket>) {
        if (response.isSuccessful) {
            val ticket = response.body()
            val user = UserFactory.extractUser(ticket!!)

            userStorage
                    .saveUser(user)
                    .saveUserTicket(ticket)

            sendResult(true)
        } else {
            sendResult(false, "회원 정보가 유효하지 않습니다.")
        }
    }

    override fun onFailure(call: Call<Ticket>, t: Throwable) {
        sendResult(false, "알 수 없는 이유로 로그인에 실패했습니다.")
    }
}
