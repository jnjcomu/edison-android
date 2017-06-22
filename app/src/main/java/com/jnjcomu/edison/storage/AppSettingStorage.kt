package com.jnjcomu.edison.storage

import android.content.Context
import android.content.SharedPreferences

/**
 * @author kimwoojae <wj1187></wj1187>@naver.com>
 * *
 * @since 2017-05-25
 */

class AppSettingStorage private constructor(private val context: Context) {

    private val mPref: SharedPreferences
    private val mEditor: SharedPreferences.Editor

    var isActiveScanning: Boolean = false
        private set

    init {

        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        mEditor = mPref.edit()

        isActiveScanning = mPref.getBoolean(ACTIVITED_PREF, false)
    }

    fun saveActive(isActive: Boolean) {
        this.isActiveScanning = isActive
        mEditor.putBoolean(ACTIVITED_PREF, isActive).commit()
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

        private var instance: AppSettingStorage? = null

        fun getInstance(context: Context): AppSettingStorage {
            if (instance == null) instance = AppSettingStorage(context)

            return instance!!
        }
    }
}