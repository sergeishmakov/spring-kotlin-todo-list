package com.security

import io.jsonwebtoken.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest


const val ONE_HOUR_MILLISECONDS: Int = 60 * 60 * 1000
const val SECRET_KEY: String = "secret"
const val EXPIRATION_TIME = ONE_HOUR_MILLISECONDS * 24

@Component
class JwtTokenProvider (){
    fun generateToken(userEmail: String): String {
        val claims = Jwts.claims().setSubject(userEmail)
        val validity = Date(System.currentTimeMillis() + EXPIRATION_TIME)

        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact()
    }

    fun getExpiration(token: String): Date {
        val claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
        return claimsJws.body.expiration
    }

    fun isExpiredToken(token: String): Boolean {
        return getExpiration(token).before(Date())
    }

    val logger: Logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)

    fun validate(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
            return true
        } catch (ex: SignatureException) {
            logger?.error("Invalid JWT signature - {}", ex.message)
        } catch (ex: MalformedJwtException) {
            logger?.error("Invalid JWT token - {}", ex.message)
        } catch (ex: ExpiredJwtException) {
            logger?.error("Expired JWT token - {}", ex.message)
        } catch (ex: UnsupportedJwtException) {
            logger?.error("Unsupported JWT token - {}", ex.message)
        } catch (ex: IllegalArgumentException) {
            logger?.error("JWT claims string is empty - {}", ex.message)
        }
        return false
    }

    fun getUserEmail(token: String): String {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).body.subject
    }

    fun resolveToken(request: HttpServletRequest): String {
        return try {
            val header = request.getHeader(HttpHeaders.AUTHORIZATION);
            return header.split(" ")[1].trim();

        } catch (e: NullPointerException) {
            ""
        }
    }
}