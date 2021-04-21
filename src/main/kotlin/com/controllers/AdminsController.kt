package com.controllers

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/admins")
class AdminsController() {

  @GetMapping("hello")
  @PreAuthorize("hasAuthority('developers:write')")
  fun hello():String{
    return "Hello Admin!"
  }
}
