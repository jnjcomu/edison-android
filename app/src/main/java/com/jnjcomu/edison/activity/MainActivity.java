package com.jnjcomu.edison.activity;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.jnjcomu.edison.EdisonApplication;
import com.jnjcomu.edison.R;
import com.jnjcomu.edison.callback.CloudEventListener;
import com.jnjcomu.edison.factory.InterpolatorFactory;
import com.jnjcomu.edison.storage.AppSettingStorage;
import com.jnjcomu.edison.ui.AnimationManager;
import com.loplat.placeengine.Plengi;
import com.loplat.placeengine.PlengiResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements CloudEventListener, PermissionListener {

    protected AppSettingStorage settingStorage;

    protected Plengi plengi;

    @App
    protected EdisonApplication application;

    @Bean
    protected AnimationManager animationManager;

    @ViewById
    protected TextView txtPlace;

    @ViewById
    protected RelativeLayout rlRoot;

    @ViewById
    protected ImageView imgLogo;

    @ViewById
    protected Switch swtScanning;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        application.destroyEventListener();
        settingStorage.saveActive(swtScanning.isChecked());
    }

    @AfterViews
    protected void initPermission() {
        new TedPermission(this)
                .setPermissionListener(this)
                .setDeniedMessage("권한 허가가 되지 않으면 앱을 이용하실 수 없습니다.")
                .setPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();
    }

    @Click
    protected void btnRefresh() {
        txtPlace.setText("스캔중...");
        swtScanning(true);
    }

    @CheckedChange
    protected void swtScanning(boolean isChecked) {
        if (isChecked) {
            animationManager.startAnim(
                    imgLogo,
                    R.anim.logo_vibrate
            );
            plengi.start();
            plengi.refreshPlace();
        } else {
            animationManager.cancelAnim(imgLogo);
            plengi.stop();
        }
    }

    /**
     * EventListener
     *
     * @param response PlengiResponse
     * @see CloudEventListener
     */
    @Override
    public void onPlaceDefault(PlengiResponse response) {
        if(response.place==null) {
            String msg = "등록되지 않은 장소입니다.";
            display(msg);
        } else {
            String msg = "현재 계신 장소는 " + response.place.name + "입니다.";
            display(msg);
        }
    }

    /**
     * EventListener
     *
     * @param response PlengiResponse
     * @see CloudEventListener
     */
    @Override
    public void onPlaceNear(PlengiResponse response) {
        String msg = "현재 " + response.place.name + " 주변 입니다.";
        display(msg);
    }

    /**
     * EventListener
     *
     * @param response PlengiResponse
     * @see CloudEventListener
     */
    @Override
    public void onPlaceIn(PlengiResponse response) {
        String msg = "현재 " + response.place.name + "에 입실하셨습니다.";
        display(msg);
    }

    /**
     * @param msg String
     *
     * TODO 이거 버그 해결좀;;
     * animationManager를 위에 놓으면 animation만 작동후 setText 작동 안함, setText를 위에 놓으면 setText만 작동후 animation 작동 안함
     * ㄹㅇ 개빡침 어노테이션이 문제인건지 안드로이드가 문제인건지 모르겠음 ㅇㅇ
     */
    public void display(String msg) {
        animationManager.restartAnim(
                imgLogo,
                R.anim.logo_scale,
                InterpolatorFactory.getDefaultLogoInterpolator()
        );
        txtPlace.setText(msg);
    }

    @Override
    public void onPermissionGranted() {
        application.setEventListener(this);
        plengi = application.getPlengi();

        settingStorage = AppSettingStorage.getInstance(this);

        boolean isActiveScanning = settingStorage.isActiveScanning();
        swtScanning.setChecked(isActiveScanning);

        if (isActiveScanning) {
            plengi.start();
            plengi.refreshPlace();
        }
    }

    @Override
    public void onPermissionDenied(ArrayList<String> arrayList) {

    }
}
