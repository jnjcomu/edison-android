package com.jnjcomu.edison.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-12
 */

public class NoticeRegionResponse {
    @SerializedName("messsage") private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
