package com.security

import com.repositories.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException

@Component
class JwtTokenFilter(val userRepository: UserRepository,  val tokenProvider: JwtTokenProvider) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = tokenProvider.resolveToken(request)

        try {
            if (!token.isEmpty() && tokenProvider.validateToken(token)) {
                SecurityContextHolder.getContext().authentication = tokenProvider.getAuthentication(token)
            }

        } catch(e: JwtAuthenticationException){
            response.sendError(e.httpStatus.value(), e.message)
        }

            try {
                filterChain.doFilter(request, response)

            } catch(e: AccessDeniedException ) {
                response.writer.write("Yes, Error!")
            }


    }
}
