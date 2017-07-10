package com.jnjcomu.edison.model

import com.google.gson.annotations.SerializedName

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-09
 */

data class Ticket(
        @SerializedName("token") var ticketCode: String?
)
