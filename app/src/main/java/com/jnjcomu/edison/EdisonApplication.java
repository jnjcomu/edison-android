package com.jnjcomu.edison;

import android.app.Application;

import com.jnjcomu.edison.callback.CloudEventListener;
import com.jnjcomu.edison.cloud.LoplatCloudListener;
import com.jnjcomu.edison.storage.Configuration;
import com.loplat.placeengine.Plengi;

import org.androidannotations.annotations.EApplication;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-12
 */

@EApplication
public class EdisonApplication extends Application {
    private static EdisonApplication instance;

    private Plengi plengi;
    private LoplatCloudListener cloudlistener;

    @Override
    public void onCreate() {
        super.onCreate();

        // NOTE : Please register when login into edison server

        cloudlistener = new LoplatCloudListener();
        plengi = Plengi.getInstance(this);
        plengi.setListener(cloudlistener);

        Configuration conf = new Configuration(this);

        // Please change the "12345" be named user unique code to unique.
        plengi.init(conf.getLoplatId(), conf.getLoplatPw(), "12345");

        instance = this;
    }

    public Plengi getPlengi() {
        return plengi;
    }

    public void setEventListener(CloudEventListener cloudEventListener) {
        if(cloudlistener != null) {
            cloudlistener.setListener(cloudEventListener);
        }
    }

    public void destroyEventListener() {
        cloudlistener = null;
    }

    public static EdisonApplication getInstance() {
        return instance;
    }
}
