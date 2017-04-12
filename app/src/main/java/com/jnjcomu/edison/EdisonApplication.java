package com.jnjcomu.edison;

import android.app.Application;
import android.content.Context;

import com.loplat.placeengine.Plengi;
import com.loplat.placeengine.utils.LoplatLogger;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

public class EdisonApplication extends Application {

    Plengi mPlengi = null;

    private static EdisonApplication instance;

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // set up loplat engine
        mPlengi = Plengi.getInstance(this);
        //mPlengi.setListener(new LoplatPlengiListener());
    }

}
