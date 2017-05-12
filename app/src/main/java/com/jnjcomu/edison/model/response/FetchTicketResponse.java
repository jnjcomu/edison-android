package com.jnjcomu.edison.model.response;

import com.google.gson.annotations.SerializedName;
import com.jnjcomu.edison.model.Ticket;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-11
 */

public class FetchTicketResponse {
    @SerializedName("response_code")
    private int responseCode;

    @SerializedName("ticket")
    private Ticket ticket;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
