package com.controllers

import com.dtos.RefreshTokenDto
import com.dtos.SignInDTO
import com.models.User
import com.security.JwtTokenProvider
import com.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@RestController
@RequestMapping("/session")
class SessionController(
    val userService: UserService,
    val passwordEncoder: PasswordEncoder,
    val jwtTokenProvider: JwtTokenProvider,
    val authenticationManager: AuthenticationManager
    ) {

    @PostMapping("/sign_up")
    @PreAuthorize("permitAll()")
    fun signUp(@Valid @RequestBody data: User): Boolean {

        data.password = passwordEncoder.encode(data.password)
        var result = userService.createUser(data)

        return true
    }

    @PostMapping("/sign_in")
    @PreAuthorize("permitAll()")
    fun signIn(@Valid @RequestBody body: SignInDTO): ResponseEntity<Any> {
        try {
            authenticationManager?.authenticate(
                UsernamePasswordAuthenticationToken(
                    body.email,
                    body.password
                )
            )
            val user: User = userService.getUserByEmail(body.email)
                ?: throw UsernameNotFoundException("User doesn't exists")

            val token: String = jwtTokenProvider.generateToken(body.email)
            val response: MutableMap<String, String> = HashMap()

            response["email"] = body.email
            response["token"] = token
            return ResponseEntity.ok(response)
        } catch (e: AuthenticationException) {
            return ResponseEntity("Invalid email/password combination", HttpStatus.FORBIDDEN)
        }
    }

    @PostMapping("/refresh_token")
    fun refreshToken(@RequestBody body: RefreshTokenDto): ResponseEntity<Any>{
        if(!jwtTokenProvider.validate(body.token)) return ResponseEntity("Invalid token", HttpStatus.FORBIDDEN)
        val user = userService.getUserByEmail(jwtTokenProvider.getUserEmail(body.token))
            ?: return ResponseEntity("user not found", HttpStatus.FORBIDDEN)
        val token: String = jwtTokenProvider.generateToken(user.email)
        val response: MutableMap<String, String> = HashMap()

        response["email"] = user.email
        response["token"] = token
        return ResponseEntity.ok(response)
    }

    @PostMapping("/signOut")
    fun signOut(req: HttpServletRequest, res: HttpServletResponse) {
       SecurityContextLogoutHandler().logout(req, res, null)
    }


}
