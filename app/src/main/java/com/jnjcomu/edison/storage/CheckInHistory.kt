package com.jnjcomu.edison.storage

import android.content.Context
import android.content.SharedPreferences
import java.text.DateFormat
import java.util.*

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-26
 */

class CheckInHistory(context: Context) {
    private val mPref: SharedPreferences
    private val mEditor: SharedPreferences.Editor

    init {
        mPref = context.getSharedPreferences(CheckInHistory.PREF_NAME, Context.MODE_PRIVATE)
        mEditor = mPref.edit()
    }

    fun getFirst(): Boolean? {
        val date = Date()
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)

        val isCheckedIn = mPref.getBoolean("${df.format(date)}-1", false)
        return isCheckedIn
    }

    fun getSecond(): Boolean? {
        val date = Date()
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)

        val isCheckedIn = mPref.getBoolean("${df.format(date)}-2", false)
        return isCheckedIn
    }

    fun checkInFirst() {
        val date = Date()
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)

        mEditor.putBoolean("${df.format(date)}-1", true)
    }

    fun checkInSecond() {
        val date = Date()
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)

        mEditor.putBoolean("${df.format(date)}-2", true)
    }

    companion object {
        private val PREF_NAME = "checkin"
    }

}