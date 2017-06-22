package com.jnjcomu.edison

import android.app.Application

import com.jnjcomu.edison.callback.CloudEventListener
import com.jnjcomu.edison.cloud.LoplatCloudListener
import com.jnjcomu.edison.storage.Configuration
import com.loplat.placeengine.Plengi

import org.androidannotations.annotations.EApplication

/**
 * @author CodeRi13 <ruto1924></ruto1924>@gmail.com>
 * *
 * @since 2017-04-12
 */

@EApplication
class EdisonApplication : Application() {

    var plengi: Plengi? = null
        private set
    private var cloudlistener: LoplatCloudListener? = null

    override fun onCreate() {
        super.onCreate()

        // NOTE : Please register when login into edison server

        cloudlistener = LoplatCloudListener()
        plengi = Plengi.getInstance(this)
        plengi!!.listener = cloudlistener

        val conf = Configuration(this)

        // Please change the "12345" be named user unique code to unique.
        plengi!!.init(conf.loplatId, conf.loplatPw, "12345")

        instance = this
    }

    fun setEventListener(cloudEventListener: CloudEventListener) {
        if (cloudlistener != null) {
            cloudlistener!!.setListener(cloudEventListener)
        }
    }

    fun destroyEventListener() {
        cloudlistener = null
    }

    companion object {
        var instance: EdisonApplication? = null
            private set
    }
}
