package com.jnjcomu.edison.factory;

import android.support.annotation.NonNull;

import com.jnjcomu.edison.model.Ticket;
import com.jnjcomu.edison.model.User;

import io.jsonwebtoken.Claims;
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
        int userSerial = claims.get("serial", Integer.class);
        String userName = claims.get("name", String.class);
        String userType = claims.get("userType", String.class);

        return new User(
                userID,
                userName,
                extractGradeFromSerial(userSerial),
                extractClazzFromSerial(userSerial),
                extractNumberFromSerial(userSerial)
        );
    }

    public static int extractGradeFromSerial(int serial) {
        return serial / 1000;
    }

    public static int extractClazzFromSerial(int serial) {
        return (serial / 100) % 10;
    }

    public static int extractNumberFromSerial(int serial) {
        return serial - (serial / 100 * 100);
    }
}
