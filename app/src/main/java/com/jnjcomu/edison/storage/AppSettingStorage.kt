package com.jnjcomu.edison.storage

import android.content.Context
import android.content.SharedPreferences

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-05-25
 */

class AppSettingStorage(context: Context) {
    private val mPref: SharedPreferences
    private val mEditor: SharedPreferences.Editor

    var isActiveScanning: Boolean = false
        private set

    var isFirstRun: Boolean = false
        private set

    init {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        mEditor = mPref.edit()

        isActiveScanning = mPref.getBoolean(ACTIVITED_PREF, false)
        isFirstRun = mPref.getBoolean(FIRSTRUN_PREF, true)
    }

    fun saveActive(isActive: Boolean) {
        this.isActiveScanning = isActive
        mEditor.putBoolean(ACTIVITED_PREF, isActive).commit()
    }

    fun saveFirstrun(isActive: Boolean) {
        this.isFirstRun = isActive
        mEditor.putBoolean(FIRSTRUN_PREF, isActive).commit()
    }

    fun activeScanning() {
        saveActive(true)
    }

    fun inactiveScanning() {
        saveActive(false)
    }

    companion object {
        private val PREF_NAME = "app_setting"
        private val ACTIVITED_PREF = "activited"
        private val FIRSTRUN_PREF = "firstrun"
    }
}