package com.security

import com.dtos.SafeUser
import com.repositories.UserRepository
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.List
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtTokenFilter(val userRepository: UserRepository,  val tokenProvider: JwtTokenProvider) : OncePerRequestFilter() {
    protected override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = tokenProvider.resolveToken(request)

        if(token.isEmpty()) return filterChain.doFilter(request, response)

        if(!tokenProvider.validate(token)){
            return filterChain.doFilter(request, response)
        }

        if (tokenProvider.isExpiredToken(token)) {
            return filterChain.doFilter(request, response)
        }

        val user = userRepository
            .findByEmail(tokenProvider.getUserEmail(token))
            ?: return filterChain.doFilter(request, response)


        val userDetails = SecurityUser.fromUser(user)

        val authentication = UsernamePasswordAuthenticationToken(
            userDetails, null,
            user?.role?.authorities
        )
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}
