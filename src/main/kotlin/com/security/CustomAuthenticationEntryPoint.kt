package com.security

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint{
    override fun commence(req: HttpServletRequest?, res: HttpServletResponse, authException: AuthenticationException) {
        print("=====================")
        print(res)
        print("=====================")
        res?.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.name)
        res?.writer.write(HttpStatus.FORBIDDEN.name)
    }
}