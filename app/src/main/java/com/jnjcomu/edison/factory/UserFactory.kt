package com.jnjcomu.edison.factory

import com.jnjcomu.edison.model.Ticket
import com.jnjcomu.edison.model.User

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-24
 */

object UserFactory {

    fun extractUser(ticket: Ticket): User {
        val token = ticket.ticketCode
        val claims = Jwts.parser()
                .parseClaimsJws(token)
                .body

        val userID = claims.get("id", Int::class.java)
        val userSerial = claims.get("serial", Int::class.java)
        val userName = claims.get("name", String::class.java)
        val userType = claims.get("userType", String::class.java)

        return User(
                userID,
                userName,
                extractGradeFromSerial(userSerial),
                extractClazzFromSerial(userSerial),
                extractNumberFromSerial(userSerial)
        )
    }

    fun extractGradeFromSerial(serial: Int): Int {
        return serial / 1000
    }

    fun extractClazzFromSerial(serial: Int): Int {
        return serial / 100 % 10
    }

    fun extractNumberFromSerial(serial: Int): Int {
        return serial - serial / 100 * 100
    }
}
