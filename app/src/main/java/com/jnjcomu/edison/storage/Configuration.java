package com.jnjcomu.edison.storage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-13
 */

public class Configuration {
    private static final String TAG = "Configuration";
    private static final String ENCODING = "UTF-8";
    private static final String CONFIGURATION_JSON_NAME = "Edison.json";

    private JSONObject json;
    private Context context;

    private String clientKey;

    public Configuration(Context context) {
        this.context = context;

        InputStream inputStream = null;
        Encrypter encrypt = new Encrypter(context);

        try {
            // read edison.json file
            inputStream = context.getAssets().open(CONFIGURATION_JSON_NAME);

            int size = inputStream.available();
            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            String readString = new String(buffer, ENCODING);

            // get json object
            json = new JSONObject(readString);

            // get decrypted key
            String clientKeyEncrypted = json.getString("client_key");
            clientKey = encrypt.decrypt(clientKeyEncrypted);
        } catch (Exception ignored) {
            Log.d(TAG, "Cant read json from assets");
        } finally {
            try {
                inputStream.close();
            } catch (Exception ignored) {
                Log.d(TAG, "Fail to stop work");
            }
        }
    }

    @NonNull
    public String getClientKey() {
        return clientKey;
    }
}
