package com.jnjcomu.edison.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.jnjcomu.edison.R
import com.jnjcomu.edison.addition.*
import com.jnjcomu.edison.callback.CloudEventListener
import com.jnjcomu.edison.factory.InterpolatorFactory
import com.loplat.placeengine.PlengiResponse
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), CloudEventListener, PermissionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!appStorage.permissionGranted)
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

        plengi.stop()
        inApplication.destroyEventListener()
    }

    private fun initUi() {
        img_logo.startAnim(this, R.anim.logo_vibrate)

        swt_scanning.setOnCheckedChangeListener { _, isChecked ->
            appStorage.saveActive(swt_scanning.isChecked)
            if (isChecked) {
                Toast.makeText(this, "위치 전송 기능이 활성화되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "위치 전송 기능이 비활성화되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        btn_refresh.setOnClickListener {
            img_logo.startAnim(this, R.anim.logo_vibrate)
            txt_place.text = "스캔중..."
            swt_scanning.isChecked = true
            plengi.refreshPlace()
        }

        btn_settings.setOnClickListener {
            startActivity(Intent(applicationContext, SettingsActivity::class.java))
        }
    }

    /**
     * EventListener
     *
     * @param response PlengiResponse
     *
     * @see CloudEventListener
     */
    override fun onPlaceDefault(response: PlengiResponse) =
        displayPlace(
                if (response.place == null) {
                    "등록되지 않은 장소입니다."
                } else {
                    "현재 계신 장소는 ${response.place.name} 입니다."
                }
        )

    /**
     * EventListener
     *
     * @param response PlengiResponse
     *
     * @see CloudEventListener
     */
    override fun onPlaceNear(response: PlengiResponse) =
            displayPlace("현재 ${response.place.name} 주변에 있습니다.")

    /**
     * EventListener
     *
     * @param response PlengiResponse
     *
     * @see CloudEventListener
     */
    override fun onPlaceIn(response: PlengiResponse) =
            displayPlace("현재 ${response.place.name} 안에 있습니다.")

    /**
     * @param msg String
     * TODO 이거 ㄹㅇ 해결 안됨;;
     *
     */
    private fun displayPlace(msg: String) {
        txt_place.text = msg
        img_logo.startAnim(
                this, R.anim.logo_scale,
                InterpolatorFactory.makeLogoInterpolator()
        )
    }

    override fun onPermissionGranted() {
        initUi()

        if(!netManager.isActive()) {
            netManager.activeWifi()
        }

        swt_scanning.isChecked = appStorage.isActiveScanning

        inApplication.registerEventListener(this)
        plengi.start()
        plengi.refreshPlace()
    }

    override fun onPermissionDenied(arrayList: ArrayList<String>) {

    }

    fun checkIn(v: View) {
        startActivity(Intent(this, CheckInActivity::class.java))
    }

}
