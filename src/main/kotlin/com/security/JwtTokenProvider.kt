package com.security

import com.security.JwtAuthenticationException
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest
import io.jsonwebtoken.MalformedJwtException

const val ONE_HOUR_MILLISECONDS: Int = 60 * 60 * 1000
const val SECRET_KEY: String = "secret"
const val EXPIRATION_TIME = ONE_HOUR_MILLISECONDS * 24
const val AUTHORIZATION_HEADER = "Authorization"

@Component
class JwtTokenProvider (@Qualifier("userDetailsServiceImpl") val userDetailsService: UserDetailsService){
    fun generateToken(userEmail: String): String {
        val claims = Jwts.claims().setSubject(userEmail)
        val validity = Date(System.currentTimeMillis() + EXPIRATION_TIME)

        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact()
    }


    fun validateToken(token: String?): Boolean {
         try {
             val claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
             if (claimsJws.body.expiration.before(Date())) throw JwtAuthenticationException(
                 "expired_token",
                 HttpStatus.UNAUTHORIZED
             )
             return true
         } catch (e: JwtException) {
            throw JwtAuthenticationException("unauthorized", HttpStatus.UNAUTHORIZED)
        } catch (e: IllegalArgumentException) {
            throw JwtAuthenticationException("unauthorized", HttpStatus.UNAUTHORIZED)
        }
    }

    fun getAuthentication(token: String): Authentication{
        val userDetails = userDetailsService.loadUserByUsername(getUserEmail(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
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