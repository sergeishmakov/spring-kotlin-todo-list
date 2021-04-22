package com.controllers

import com.services.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
@PreAuthorize("hasAuthority('users:read')")
class UsersController(val userService: UserService) {

  @GetMapping("hello")
  fun getUsers():String{
    return "Hello User!"
  }
}
