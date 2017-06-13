package com.jnjcomu.edison.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-09
 */

public class Ticket {
    @SerializedName("token")
    private String ticketCode;

    public Ticket(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }
}
