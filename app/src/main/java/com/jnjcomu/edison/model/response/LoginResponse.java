package com.jnjcomu.edison.model.response;

import com.google.gson.annotations.SerializedName;
import com.jnjcomu.edison.model.User;

/**
 * @author CodeRi <ruto1924@gmail.com>
 * @since 2017-05-07
 */

public class LoginResponse {
    @SerializedName("response_code") private Integer reponseCode;
    @SerializedName("message") private String message;
    @SerializedName("user_data") private User userData;

    public Integer getReponseCode() {
        return reponseCode;
    }

    public String getMessage() {
        return message;
    }

    public User getUserData() {
        return userData;
    }
}
