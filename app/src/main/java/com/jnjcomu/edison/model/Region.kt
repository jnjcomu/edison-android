package com.jnjcomu.edison.model

import com.google.gson.annotations.SerializedName

/**
 * @author CodeRi13 <ruto1924></ruto1924>@gmail.com>
 * *
 * @since 2017-05-12
 */

class Region(@SerializedName("region") var regionID: Int, @SerializedName("name") var placeName: String)
