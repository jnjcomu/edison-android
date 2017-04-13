package com.jnjcomu.edison.module;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kimwoojae on 2017. 4. 5.
 */

public class LoadJson {

    public static String[] loadloplat(Context mContext) {
        String[] ls = new String[2];
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(mContext));
            JSONObject edison = obj.getJSONObject("Edison");
            JSONObject jo_inside = edison.getJSONObject("loplat");
            String id_value = jo_inside.getString("id");
            String pw_value = jo_inside.getString("pw");
            ls[0] = id_value;
            ls[1] = pw_value;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ls;
    }

    private static String loadJSONFromAsset(Context mContext) {
        String json;
        try {
            InputStream is = mContext.getAssets().open("Edison.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
