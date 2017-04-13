package com.jnjcomu.edison.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

public class EdisonGson {

    @SerializedName("user_type") String usertype;

    String getUsertype() {
        return usertype;
    }

}
