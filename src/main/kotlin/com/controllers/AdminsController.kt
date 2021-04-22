package com.controllers

import com.models.Permission
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/admins")
class AdminsController() {

  @GetMapping("hello")
  @PreAuthorize("hasAuthority('admins:read')")
  fun hello():String{
    return "Hello Admin!"
  }
}
