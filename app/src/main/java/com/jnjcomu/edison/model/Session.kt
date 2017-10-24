package com.jnjcomu.edison.model

import com.google.gson.annotations.SerializedName

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-23
 */
class Session(
        @SerializedName("__ci_last_regenerate") var last: String,
        @SerializedName("send") var login: String,
        @SerializedName("name") var name: String
)
