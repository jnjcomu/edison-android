package com.jnjcomu.edison.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.jnjcomu.edison.R
import com.jnjcomu.edison.addition.*
import com.jnjcomu.edison.broadcast.BootBroadcastReceiver
import com.jnjcomu.edison.callback.CloudEventListener
import com.jnjcomu.edison.factory.InterpolatorFactory
import com.jnjcomu.edison.broadcast.EdisonReceiver
import com.loplat.placeengine.PlengiResponse
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), CloudEventListener, PermissionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TedPermission(this)
                .setPermissionListener(this)
                .setDeniedMessage("권한 허가가 되지 않으면 앱을 이용하실 수 없습니다.")
                .setPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .check()
    }

    override fun onDestroy() {
        super.onDestroy()

        inApplication.destroyEventListener()
        appStorage.saveActive(swt_scanning.isChecked)
    }

    fun initUi() {
        inApplication.registerEventListener(this)

        swt_scanning.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                img_logo.startAnim(this, R.anim.logo_vibrate)

                plengi.start()
                plengi.refreshPlace()
            } else {
                plengi.stop()
                img_logo.cancelAnim()
            }
        }

        btn_refresh.setOnClickListener {
            img_logo.startAnim(this, R.anim.logo_vibrate)
            txt_place.text = "스캔중..."
            swt_scanning.isChecked = true
            plengi.refreshPlace()
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
    fun displayPlace(place: String) {
        txt_place.text = place
        img_logo.restartAnim(
                this, R.anim.logo_scale,
                InterpolatorFactory.makeLogoInterpolator()
        )
    }

    override fun onPermissionGranted() {
        initUi()

        if(appStorage.isFirstRun)
            firstRun()

        if(!netChecker.isActive()) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("안내")
            builder.setMessage("Edison 서비스의 정상 동작을 위해 Wi-Fi를 활성화시켜주세요.")
            finish()
        }

        swt_scanning.isChecked = appStorage.isActiveScanning

        if (swt_scanning.isChecked) {
            plengi.start()
            plengi.refreshPlace()
        }
    }

    override fun onPermissionDenied(arrayList: ArrayList<String>) {

    }

    fun firstRun() {
        sendBroadcast(Intent(this, BootBroadcastReceiver::class.java))

        appStorage.saveFirstrun(false)
    }

}
