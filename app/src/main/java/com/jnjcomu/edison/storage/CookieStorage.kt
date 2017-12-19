package com.jnjcomu.edison.storage

import android.content.Context
import android.content.SharedPreferences

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-09-29
 */

class CookieStorage(context: Context) {
    private val mPref: SharedPreferences
    private val mEditor: SharedPreferences.Editor

    init {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        mEditor = mPref.edit()
    }

    fun getCookie(): HashSet<String>? {
        val cookie = mPref.getStringSet("cookie", null) as HashSet<String>?
        return cookie
    }

    fun putCookie(cookie: HashSet<String>) {
        mEditor.putStringSet("cookie", cookie).commit()
    }

    fun clear() {
        mEditor.clear().commit()
    }

    companion object {
        private val PREF_NAME = "cookie"
    }
}