package com.controllers

import com.dtos.SignInDTO
import com.services.UserService
import org.springframework.web.bind.annotation.*
import com.models.User
import com.services.AuthService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import org.springframework.security.core.context.SecurityContext

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken




val invalidResponseEntity: ResponseEntity<Any>  = ResponseEntity.badRequest().body("invalid email or password")



@RestController
@RequestMapping("/session")
class SessionController(val userService: UserService, private var passwordEncoder: PasswordEncoder) {

    @PostMapping("/sign_up")
    fun signUp(@Valid @RequestBody data: User): Boolean {

        data.password = passwordEncoder.encode(data.password).toString()
        var result = userService.createUser(data)

        return true
    }

    @PostMapping("/sign_in")
    fun signIn(@Valid @RequestBody data: SignInDTO, response: HttpServletResponse, authService: AuthService): ResponseEntity<Any> {
        val user = userService.getUserByEmail(data.email)
            ?: return invalidResponseEntity

        if (user.password == passwordEncoder.encode(data.password)) {
            return invalidResponseEntity
        }

        val issuer = user.id.toString()

        val token = authService.buildToken(issuer)

        return ResponseEntity.ok(token)
    }
}
