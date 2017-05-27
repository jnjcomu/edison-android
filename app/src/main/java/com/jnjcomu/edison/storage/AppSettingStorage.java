package com.jnjcomu.edison.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-05-25
 */

public class AppSettingStorage {
    private static final String PREF_NAME = "app_setting";

    private static final String ACTIVITED_PREF = "activited";

    private static AppSettingStorage instance = null;

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    private boolean isActiveScanning;

    private Context context;

    private AppSettingStorage(Context context) {
        this.context = context;

        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();

        isActiveScanning = mPref.getBoolean(ACTIVITED_PREF, false);
    }

    public static AppSettingStorage getInstance(Context context) {
        if(instance == null) instance = new AppSettingStorage(context);

        return instance;
    }

    public void saveActive(boolean isActive) {
        this.isActiveScanning = isActive;
        mEditor.putBoolean(ACTIVITED_PREF, isActive).commit();
    }

    public boolean isActiveScanning() {
        return isActiveScanning;
    }

    public void activeScanning() {
        saveActive(true);
    }

    public void inactiveScanning() {
        saveActive(false);
    }
}