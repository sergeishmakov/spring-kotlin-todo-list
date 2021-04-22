package com.security

import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.access.AccessDeniedHandlerImpl
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class CustomAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: org.springframework.security.access.AccessDeniedException?
    ) {
        print("=====================")
        print("Handler")
        print("=====================")

        val defaultAccessDeniedHandler: AccessDeniedHandler = AccessDeniedHandlerImpl()
        defaultAccessDeniedHandler.handle(request, response, accessDeniedException)
    }
}