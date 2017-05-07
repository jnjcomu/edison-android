package com.jnjcomu.edison.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.jnjcomu.edison.model.User;

/**
 * @author CodeRi <ruto1924@gmail.com>
 * @since 2017-05-07
 */

public class UserStorage {
    private static UserStorage instance;
    public static final String TAG = "UserStorage";

    private static final String STORAGE_NAME = "user_storage";
    private static final int SHARED_PREFERENCE_MODE = Context.MODE_PRIVATE;

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    private String userName;
    private Integer userGrade;
    private Integer userClazz;
    private Integer userNumber;
    private String userAuthKey;

    public static UserStorage getInstance(Context context) {
        if(instance == null) instance = new UserStorage(context);

        return instance;
    }

    private UserStorage(Context context) {
        mPref = context.getSharedPreferences(STORAGE_NAME, SHARED_PREFERENCE_MODE);
        mEditor = mPref.edit();

        userName = mPref.getString("name", "");
        userGrade = mPref.getInt("grade", 0);
        userClazz = mPref.getInt("class", 0);
        userNumber = mPref.getInt("number", 0);

        // TODO : Pleasssssse encrypt auth key when save!
        userAuthKey = mPref.getString("authKey", "");
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserGrade() {
        return userGrade;
    }

    public Integer getUserClazz() {
        return userClazz;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public String getUserAuthKey() {
        return userAuthKey;
    }

    public User toUser() {
        User user = new User();

        user.setName(userName);
        user.setClazz(userClazz);
        user.setGrade(userGrade);
        user.setNumber(userNumber);
        user.setAuthKey(userAuthKey);

        return user;
    }
}
