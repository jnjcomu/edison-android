package com.jnjcomu.edison;

import android.app.Application;

import com.jnjcomu.edison.callback.CloudEventListener;
import com.jnjcomu.edison.cloud.LoplatCloudListener;
import com.loplat.placeengine.Plengi;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-12
 */

public class EdisonApplication extends Application {
    private static EdisonApplication instance;

    private Plengi plengi;
    private LoplatCloudListener cloudlistener;

    @Override
    public void onCreate() {
        super.onCreate();

        cloudlistener = new LoplatCloudListener();
        plengi = Plengi.getInstance(this);
        plengi.setListener(cloudlistener);

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

    public static EdisonApplication getInstance() {
        return instance;
    }
}
