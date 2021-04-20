package com.controllers

import com.services.UserService
import org.springframework.web.bind.annotation.*
import com.models.User
import org.springframework.security.crypto.password.PasswordEncoder
import javax.validation.Valid

@RestController
@RequestMapping("/session")
class SessionController(val userService: UserService, private var passwordEncoder: PasswordEncoder) {

    @PostMapping("/sign_up")
    fun signUp(@Valid @RequestBody data: User): Boolean {

        data.password = passwordEncoder.encode(data.password).toString()
        userService.createUser(data)
        return true
    }
}
