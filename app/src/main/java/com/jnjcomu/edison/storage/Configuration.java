package com.jnjcomu.edison.storage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jnjcomu.edison.encrypt.Encrypter;

import org.json.JSONObject;

import java.io.InputStream;

/**
 * Configuration Class
 *
 * 키스토어에 의해 암호화된 키를 해독하는 클레스.
 *
 *
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-04-13
 */
public class Configuration {
    private static final String TAG = "Configuration";
    private static final String ENCODING = "UTF-8";
    private static final String CONFIGURATION_JSON_NAME = "Edison.json";

    private JSONObject json;
    private String loplatId, loplatPw, hostUrl;

    public Configuration(Context context) {
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
            JSONObject loplatJson = json.getJSONObject("loplat");

            String encryptHostUrl = json.getString("host_url");
            String encryptLoplatId = loplatJson.getString("id");
            String encryptLoplatPw = loplatJson.getString("pw");

            hostUrl = encrypt.decrypt(encryptHostUrl);
            loplatId = encrypt.decrypt(encryptLoplatId);
            loplatPw = encrypt.decrypt(encryptLoplatPw);
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
    public String getLoplatId() {
        return loplatId;
    }

    @NonNull
    public String getLoplatPw() {
        return loplatPw;
    }

    @NonNull
    public String getHostUrl() {
        return hostUrl;
    }
}
