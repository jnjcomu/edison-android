package com.jnjcomu.edison.model.response;

import com.google.gson.annotations.SerializedName;
import com.jnjcomu.edison.model.Ticket;
import com.jnjcomu.edison.model.User;

/**
 * @author CodeRi <ruto1924@gmail.com>
 * @since 2017-05-07
 */

public class LoginResponse {
    @SerializedName("response_code")
    private int responseCode;

    @SerializedName("message")
    private String message;

    @SerializedName("user_data")
    private User userData;

    @SerializedName("ticket")
    private Ticket ticket;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int reponseCode) {
        this.responseCode = reponseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
