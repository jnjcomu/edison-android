package com.jnjcomu.edison.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.jnjcomu.edison.R
import com.jnjcomu.edison.addition.startAnim
import com.jnjcomu.edison.api.API
import com.jnjcomu.edison.callback.CloudEventListener
import com.jnjcomu.edison.factory.InterpolatorFactory
import com.jnjcomu.edison.network.NetManager
import com.loplat.placeengine.Plengi
import com.loplat.placeengine.PlengiResponse
import kotlinx.android.synthetic.main.activity_checkin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckInActivity : AppCompatActivity(), CloudEventListener {

    val rooms = arrayOf("휴머실", "그린IT실")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)

        val nm = NetManager(this)

        if(!nm.isActive())
            nm.activeWifi()
        Plengi.getInstance(this).refreshPlace()

        img_logo.startAnim(this, R.anim.logo_vibrate)
    }

    override fun onPlaceDefault(response: PlengiResponse) {
        Toast.makeText(this, "로고를 터치해 체크인을 완료하세요!", Toast.LENGTH_LONG).show()
        displayPlace(
                if (response.place == null) {
                    "장소 인식에 실패했습니다."
                } else {
                    "${response.place}에 계시군요!"
                }
        )
        if (response.place != null)
            img_logo.setOnClickListener({
                checkIn(response.place.name)
                img_logo.startAnim(this, R.anim.logo_vibrate)
                tv_incorrect.visibility = View.GONE
            })

        val msg = "현재 계신 곳이 ${response.place.name}이 아닌가요?"
        tv_incorrect.text = msg
        tv_incorrect.visibility = View.VISIBLE
        tv_incorrect.setOnClickListener({
            AlertDialog.Builder(this)
                    .setTitle("")
                    .setItems(rooms, {_, index ->
                        checkIn(rooms[index])
                        img_logo.startAnim(this, R.anim.logo_vibrate)
                        tv_incorrect.visibility = View.GONE
                    })
                    .create().show()
        })
    }

    override fun onPlaceIn(response: PlengiResponse) {
    }

    override fun onPlaceNear(response: PlengiResponse) {
    }

    private fun displayPlace(msg: String) {
        tv_place.text = msg
        img_logo.startAnim(
                this, R.anim.logo_scale,
                InterpolatorFactory.makeLogoInterpolator()
        )
    }

    private fun checkIn(place: String) {
        val call = API.getApi(this)
        call.checkin(place).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                displayPlace("체크인을 완료했습니다!")
            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {

            }
        })
    }
}
