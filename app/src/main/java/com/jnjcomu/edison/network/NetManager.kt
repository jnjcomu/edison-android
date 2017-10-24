package com.jnjcomu.edison.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-08-31
 */

class NetManager(context: Context) {

    var cm: ConnectivityManager? = null
    var wm: WifiManager? = null

    var isWifiOn: Boolean = false

    init {
        wm = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        isWifiOn = wm!!.isWifiEnabled

        cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun isActive(): Boolean {
        return isWifiOn
    }

    fun isConnected(): Boolean {
        val connection = cm!!.activeNetworkInfo
        if(connection!=null && connection.isConnectedOrConnecting)
            return true
        return false
    }

    fun activeWifi() {
        wm!!.isWifiEnabled = true
    }

    fun deactiveWifi() {
        wm!!.isWifiEnabled = false
    }

}