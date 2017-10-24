package com.jnjcomu.edison.storage

import android.content.Context
import android.content.SharedPreferences

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-23
 */

class AppSettingStorage(context: Context) {
    private val mPref: SharedPreferences
    private val mEditor: SharedPreferences.Editor

    var isActiveNoti: Boolean = false
        private set

    var isFirstRun: Boolean = false
        private set

    var userName: String = "username"
        private set

    init {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        mEditor = mPref.edit()

        isActiveNoti = mPref.getBoolean(NOTI_PREF, false)
        isFirstRun = mPref.getBoolean(FIRSTRUN_PREF, true)
        userName = mPref.getString(USER_NAME, "nothing")
    }

    fun saveActive(isActive: Boolean) {
        this.isActiveNoti = isActive
        mEditor.putBoolean(NOTI_PREF, isActive).commit()
    }

    fun saveFirstrun(isActive: Boolean) {
        this.isFirstRun = isActive
        mEditor.putBoolean(FIRSTRUN_PREF, isActive).commit()
    }

    fun saveUserName(name: String) {
        this.userName = name
        mEditor.putString(USER_NAME, name).commit()
    }

    fun clear() {
        mEditor.clear()
    }

    companion object {
        private val PREF_NAME = "app_setting"
        private val NOTI_PREF = "noti"
        private val FIRSTRUN_PREF = "firstrun"
        private val USER_NAME = "username"
    }
}