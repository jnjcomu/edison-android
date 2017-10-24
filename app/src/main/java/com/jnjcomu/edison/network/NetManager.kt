package com.jnjcomu.edison.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-23
 */

class NetManager(context: Context) {

    var cm: ConnectivityManager? = null

    init {
        cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun isConnected(): Boolean {
        val connection = cm!!.activeNetworkInfo
        if(connection!=null && connection.isConnectedOrConnecting)
            return true
        return false
    }

}