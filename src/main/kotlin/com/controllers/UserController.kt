package com.controllers

import com.services.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import com.models.User


@RestController
class UserController(val userService: UserService) {

  @GetMapping
  fun getUsers():List<User> {
    return userService.getUsers()
  
}

@GetMapping


  // @GetMapping("/{id}")
  // fun show(@PathVariable id: Long) =
  //     repository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")
}
// package ru.mrsu.testdb

// import org.springframework.beans.factory.annotation.Autowired
// import org.springframework.web.bind.annotation.GetMapping
// import org.springframework.web.bind.annotation.PutMapping
// import org.springframework.web.bind.annotation.RequestBody
// import org.springframework.web.bind.annotation.RestController
// import ru.mrsu.testdb.service.UserService

// @RestController
// class UserController(
//     val userService: UserService
// ){

//     @GetMapping
//     fun getUsers():List<User> {
//         return userService.getUsers()
//     }

//     @PutMapping
//     fun saveUser(@RequestBody user: User) {
//         //val user = User(1,"Test")
//         userService.createUser(user)
//     }

// }