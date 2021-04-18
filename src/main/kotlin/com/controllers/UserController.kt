package com.controllers

import com.services.UserService
import org.springframework.web.bind.annotation.*
import com.models.User

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

  @GetMapping
  fun getUsers():List<User> {
    return userService.getUsers()
  }
}
