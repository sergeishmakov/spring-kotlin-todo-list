package com.services

import com.dtos.Token
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*

val ONE_HOUR = 60 * 60 * 1000

val ACCESS_EXPIRED_TIME = ONE_HOUR
val REFRESH_EXPIRED_TIME = ONE_HOUR * 24


class AuthService (){
    fun buildToken(issuer: String): Token {
        val accessToken = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + ACCESS_EXPIRED_TIME))
            .signWith(SignatureAlgorithm.HS512, "secret").compact()
        val refreshToken = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + REFRESH_EXPIRED_TIME))
            .signWith(SignatureAlgorithm.HS512, "secret").compact()
        return Token(accessToken, refreshToken)
    }

    fun parseToken(token: Token){

    }
}