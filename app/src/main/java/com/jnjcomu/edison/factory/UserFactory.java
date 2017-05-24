package com.jnjcomu.edison.factory;

import android.support.annotation.NonNull;

import com.jnjcomu.edison.model.Ticket;
import com.jnjcomu.edison.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-24
 */

public class UserFactory {

    @NonNull
    public static User extractUser(Ticket ticket) {
        String token = ticket.getTicketCode();
        Claims claims = Jwts.parser()
                .parseClaimsJws(token)
                .getBody();

        int userID = claims.get("id", Integer.class);

        return null;
    }
}
