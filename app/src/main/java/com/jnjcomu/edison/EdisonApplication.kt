package com.jnjcomu.edison

import android.app.Application
import com.jnjcomu.edison.addition.configurator
import com.jnjcomu.edison.addition.plengi
import com.jnjcomu.edison.callback.CloudEventListener
import com.jnjcomu.edison.cloud.LoplatCloudListener

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-12
 */

class EdisonApplication : Application() {
    private var cloudListener = LoplatCloudListener(this)

    override fun onCreate() {
        super.onCreate()

        plengi.listener = cloudListener

        /*
         * NOTE : Please register when login into edison server
         * TODO : "uniqueUserId" should be a individual code
         */
        plengi.init(configurator.loplatId, configurator.loplatPw, "12345")
    }

    fun registerEventListener(cloudEventListener: CloudEventListener) {
        cloudListener.setListener(cloudEventListener)
    }

    fun destroyEventListener() {
        cloudListener.setListener(null)
    }
}
