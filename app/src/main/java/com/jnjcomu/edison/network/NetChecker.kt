package com.jnjcomu.edison.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-08-31
 */

class NetChecker(context: Context) {

    //private var activeNetwork: NetworkInfo? = null

    var isWifiOn: Boolean = false

    init {
        val wm = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        isWifiOn = wm.isWifiEnabled
    }

    fun isActive(): Boolean {
        return isWifiOn
    }

}