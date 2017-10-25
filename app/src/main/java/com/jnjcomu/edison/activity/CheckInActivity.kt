package com.jnjcomu.edison.activity

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.jnjcomu.edison.R
import com.jnjcomu.edison.addition.*
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
import java.util.ArrayList

class CheckInActivity : AppCompatActivity(), CloudEventListener, PermissionListener {

    val rooms = arrayOf("휴머실", "그린IT실")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)

        TedPermission.with(this)
                .setPermissionListener(this)
                .setDeniedMessage("권한 허가가 되지 않으면 앱을 이용하실 수 없습니다.")
                .setPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .check()
    }

    override fun onResume() {
        super.onResume()
        swt_scanning.isChecked = appStorage.isActiveScanning
    }

    override fun onDestroy() {
        super.onDestroy()
        inApplication.destroyEventListener()
    }

    override fun onPermissionGranted() {
        val nm = NetManager(this)

        if(!nm.isActive())
            nm.activeWifi()

        inApplication.registerEventListener(this)
        Plengi.getInstance(this).refreshPlace()

        img_logo.startAnim(this, R.anim.logo_vibrate)

        swt_scanning.setOnCheckedChangeListener { _, isChecked ->
            appStorage.saveActive(swt_scanning.isChecked)
            if (isChecked) {
                Toast.makeText(this, "체크인 알림이 활성화되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "체크인 알림이 비활성화되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        btn_refresh.setOnClickListener {
            img_logo.restartAnim(this, R.anim.logo_vibrate)
            tv_place.text = "잠시만 기다려주세요..."
            plengi.refreshPlace()
        }

        btn_settings.setOnClickListener {
            startActivity(Intent(applicationContext, SettingsActivity::class.java))
        }

        btn_refresh.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        btn_settings.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
    }

    override fun onPermissionDenied(arrayList: ArrayList<String>) {
    }

    override fun onPlaceDefault(response: PlengiResponse) {
        displayPlace(
                if (response.place == null) {
                    "장소 인식에 실패했습니다."
                } else {
                    "${response.place}에 계시군요!"
                }
        )
        if (response.place != null) {
            Toast.makeText(this, "로고를 터치해 체크인을 완료하세요!", Toast.LENGTH_LONG).show()
            img_logo.setOnClickListener({
                checkIn(response.place.name)
                img_logo.restartAnim(this, R.anim.logo_vibrate)
                tv_incorrect.visibility = View.GONE
            })
        }

        val msg = "현재 계신 곳이 ${response.place.name}이 아닌가요?"
        tv_incorrect.text = msg
        tv_incorrect.visibility = View.VISIBLE
        tv_incorrect.setOnClickListener({
            AlertDialog.Builder(this)
                    .setTitle("")
                    .setItems(rooms, { _, index ->
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
        img_logo.restartAnim(
                this, R.anim.logo_scale,
                InterpolatorFactory.makeLogoInterpolator()
        )
    }

    private fun checkIn(place: String) {
        val call = API.getApi(this)
        call.checkIn(place).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                displayPlace("체크인을 완료했습니다!")
            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                displayPlace("체크인에 실패했습니다. 잠시후 다시 시도해주세요.")
            }
        })
    }
}
