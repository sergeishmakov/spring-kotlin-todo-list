package com.security

import org.springframework.http.HttpStatus
import javax.naming.AuthenticationException

class JwtAuthenticationException : AuthenticationException {
    var httpStatus: HttpStatus

    constructor(msg: String?, httpStatus: HttpStatus) : super(msg) {
        this.httpStatus = httpStatus
    }
}