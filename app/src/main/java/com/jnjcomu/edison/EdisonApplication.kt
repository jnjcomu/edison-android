package com.jnjcomu.edison

import android.app.Application
import com.jnjcomu.edison.api.plengi
import com.jnjcomu.edison.callback.CloudEventListener
import com.jnjcomu.edison.cloud.LoplatCloudListener
import com.jnjcomu.edison.storage.configurator

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-12
 */

class EdisonApplication : Application() {
    private var cloudListener = LoplatCloudListener()

    override fun onCreate() {
        super.onCreate()

        plengi.listener = cloudListener

        // NOTE : Please register when login into edison server
        // Please change the "12345" be named user unique code to unique.
        plengi.init(
                configurator.loplatId,
                configurator.loplatPw,
                "12345"
        )

        instance = this
    }

    fun registerEventListener(cloudEventListener: CloudEventListener) {
        cloudListener.setListener(cloudEventListener)
    }

    fun destroyEventListener() {
        cloudListener.setListener(null)
    }

    companion object {
        var instance: EdisonApplication? = null
            private set
    }
}
