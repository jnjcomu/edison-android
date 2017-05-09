package com.jnjcomu.edison.model.response;

import com.google.gson.annotations.SerializedName;
import com.jnjcomu.edison.model.Ticket;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-09
 */

public class CheckTicketResponse {
    @SerializedName("response_code") private Integer reponseCode;
    @SerializedName("does_need_new_ticket") private Boolean doesNeedNewTicket;
    @SerializedName("ticket") private Ticket ticket;

    public Integer getReponseCode() {
        return reponseCode;
    }

    public void setReponseCode(Integer reponseCode) {
        this.reponseCode = reponseCode;
    }

    public Boolean getDoesNeedNewTicket() {
        return doesNeedNewTicket;
    }

    public void setDoesNeedNewTicket(Boolean doesNeedNewTicket) {
        this.doesNeedNewTicket = doesNeedNewTicket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
