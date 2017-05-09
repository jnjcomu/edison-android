package com.jnjcomu.edison.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-09
 */

public class Ticket {
    @SerializedName("limit_time") private String limitDate;
    @SerializedName("ticket_code") private String ticketCode;

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }
}
