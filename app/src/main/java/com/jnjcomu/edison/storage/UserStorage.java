package com.jnjcomu.edison.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.jnjcomu.edison.model.Ticket;
import com.jnjcomu.edison.model.User;

/**
 * @author CodeRi <ruto1924@gmail.com>
 * @since 2017-05-07
 */

public class UserStorage {
    private static UserStorage instance;

    private static final String STORAGE_NAME = "user_storage";
    private static final int SHARED_PREFERENCE_MODE = Context.MODE_PRIVATE;

    private static final String USER_NAME_PREF = "user_name";
    private static final String USER_GRADE_PREF = "user_grade";
    private static final String USER_CLASS_PREF = "user_class";
    private static final String USER_NUMBER_PREF = "user_number";
    private static final String USER_TICKET_LT_PREF = "user_ticket_lt";
    private static final String USER_TICKET_CD_PREF = "user_ticket_cd";

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    private String userName;
    private Integer userGrade;
    private Integer userClazz;
    private Integer userNumber;
    private Ticket userTicket;

    public static UserStorage getInstance(Context context) {
        if(instance == null) instance = new UserStorage(context);

        return instance;
    }

    private UserStorage(Context context) {
        mPref = context.getSharedPreferences(STORAGE_NAME, SHARED_PREFERENCE_MODE);
        mEditor = mPref.edit();

        userName = mPref.getString(USER_NAME_PREF, "");
        userGrade = mPref.getInt(USER_GRADE_PREF, 0);
        userClazz = mPref.getInt(USER_CLASS_PREF, 0);
        userNumber = mPref.getInt(USER_NUMBER_PREF, 0);

        Ticket ticket = new Ticket();
        ticket.setLimitDate(mPref.getString(USER_TICKET_LT_PREF, ""));
        ticket.setTicketCode(mPref.getString(USER_TICKET_CD_PREF, ""));

        userTicket = ticket;
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

    public Ticket getUserTicket() {
        return userTicket;
    }

    public UserStorage saveUserName(String userName) {
        this.userName = userName;

        mEditor.putString(USER_NAME_PREF, userName);
        mEditor.commit();

        return this;
    }

    public UserStorage saveUserGrade(Integer userGrade) {
        this.userGrade = userGrade;

        mEditor.putInt(USER_GRADE_PREF, userGrade);
        mEditor.commit();

        return this;
    }

    public UserStorage saveUserClazz(Integer userClazz) {
        this.userClazz = userClazz;

        mEditor.putInt(USER_CLASS_PREF, userClazz);
        mEditor.commit();

        return this;
    }

    public UserStorage saveUserNumber(Integer userNumber) {
        this.userNumber = userNumber;

        mEditor.putInt(USER_NUMBER_PREF, userNumber);
        mEditor.commit();

        return this;
    }

    public UserStorage saveUserTicket(Ticket userTicket) {
        this.userTicket = userTicket;

        mEditor.putString(USER_TICKET_LT_PREF, userTicket.getLimitDate());
        mEditor.putString(USER_TICKET_CD_PREF, userTicket.getTicketCode());
        mEditor.commit();

        return this;
    }

    /**
     * NOTE : Please! this method need some time.
     */
    public void syncUserData() {
        this.userName = mPref.getString(USER_NAME_PREF, "");
        this.userGrade = mPref.getInt(USER_GRADE_PREF, 0);
        this.userClazz = mPref.getInt(USER_CLASS_PREF, 0);
        this.userNumber = mPref.getInt(USER_NUMBER_PREF, 0);
        this.userTicket.setLimitDate(mPref.getString(USER_TICKET_LT_PREF, ""));
        this.userTicket.setTicketCode(mPref.getString(USER_TICKET_CD_PREF, ""));
    }

    public User toUser() {
        User user = new User();

        user.setName(userName);
        user.setClazz(userClazz);
        user.setGrade(userGrade);
        user.setNumber(userNumber);

        return user;
    }
}
