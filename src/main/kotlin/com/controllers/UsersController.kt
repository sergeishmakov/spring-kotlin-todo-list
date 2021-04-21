package com.controllers

import com.services.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
@PreAuthorize("hasRole('USER')")
class UsersController(val userService: UserService) {

  @GetMapping("hello")
  fun getUsers():String{
    return "Hello User!"
  }
}
