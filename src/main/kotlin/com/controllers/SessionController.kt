package com.controllers

import com.services.UserService
import org.springframework.web.bind.annotation.*
import com.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import java.lang.Exception


@Autowired
private val passwordEncoder: PasswordEncoder? = null

@RestController
@RequestMapping("/session")
class SessionController(val userService: UserService) {

    @PostMapping("/sign_up")
    fun signUp(@RequestBody data: User): User {
        if (data.email.isBlank()) {
            throw Exception(
                "There is an account with that email address:" + data.email);
        }

        return userService.createUser(data)
    }
}
