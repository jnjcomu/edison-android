package com.jnjcomu.edison.activity

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*

import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.jnjcomu.edison.EdisonApplication
import com.jnjcomu.edison.R
import com.jnjcomu.edison.callback.CloudEventListener
import com.jnjcomu.edison.factory.InterpolatorFactory
import com.jnjcomu.edison.storage.AppSettingStorage
import com.jnjcomu.edison.ui.AnimationManager
import com.loplat.placeengine.Plengi
import com.loplat.placeengine.PlengiResponse
import kotlinx.android.synthetic.main.activity_main.*

import java.util.ArrayList

class MainActivity : AppCompatActivity(), CloudEventListener, PermissionListener {

    protected var application: EdisonApplication = EdisonApplication()

    protected var txtPlace: TextView = txt_place
    protected var imgLogo: ImageView = img_logo
    protected var swtScanning: Switch = swt_scanning

    protected var settingStorage: AppSettingStorage? = null
    protected var plengi: Plengi? = null

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
        application.destroyEventListener()
        settingStorage!!.saveActive(swtScanning.isChecked)
    }

    fun initUi() {
        swtScanning.setOnCheckedChangeListener {btn, isChecked ->
            if (isChecked) {
                AnimationManager.getInstance(this).startAnim(
                        imgLogo,
                        R.anim.logo_vibrate
                )
                plengi!!.start()
                plengi!!.refreshPlace()
            } else {
                AnimationManager.getInstance(this).cancelAnim(imgLogo)
                plengi!!.stop()
            }
        }
        btn_refresh.setOnClickListener {
            txtPlace.text = "스캔중..."
            swt_scanning.isChecked = true
        }
    }

    /**
     * EventListener

     * @param response PlengiResponse
     * *
     * @see CloudEventListener
     */
    override fun onPlaceDefault(response: PlengiResponse) {
        if (response.place == null) {
            val msg = "등록되지 않은 장소입니다."
            display(msg)
        } else {
            val msg = "현재 계신 장소는 " + response.place.name + "입니다."
            display(msg)
        }
    }

    /**
     * EventListener

     * @param response PlengiResponse
     * *
     * @see CloudEventListener
     */
    override fun onPlaceNear(response: PlengiResponse) {
        val msg = "현재 " + response.place.name + " 주변 입니다."
        display(msg)
    }

    /**
     * EventListener

     * @param response PlengiResponse
     * *
     * @see CloudEventListener
     */
    override fun onPlaceIn(response: PlengiResponse) {
        val msg = "현재 " + response.place.name + "에 입실하셨습니다."
        display(msg)
    }

    /**
     * @param msg String
     * *
     * * TODO 이거 버그 해결좀;;
     * * animationManager를 위에 놓으면 animation만 작동후 setText 작동 안함, setText를 위에 놓으면 setText만 작동후 animation 작동 안함
     * * ㄹㅇ 개빡침 어노테이션이 문제인건지 안드로이드가 문제인건지 모르겠음 ㅇㅇ
     * *
     * * ㄴ ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ 어노테이션 뺴봤으니까 테스트좀
     */
    fun display(msg: String) {
        AnimationManager.getInstance(this).restartAnim(
                imgLogo,
                R.anim.logo_scale,
                InterpolatorFactory.defaultLogoInterpolator
        )

        txtPlace.text = msg
    }

    override fun onPermissionGranted() {
        initUi()
        application.setEventListener(this)
        plengi = application.plengi!!

        settingStorage = AppSettingStorage.getInstance(this)

        val isActiveScanning = settingStorage!!.isActiveScanning
        swtScanning.isChecked = isActiveScanning

        if (isActiveScanning) {
            plengi!!.start()
            plengi!!.refreshPlace()
        }
    }

    override fun onPermissionDenied(arrayList: ArrayList<String>) {

    }
}
