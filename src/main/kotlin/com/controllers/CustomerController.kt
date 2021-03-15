package com.controllers

import com.services.CustomerService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import com.models.Customer


@RestController
class UserController(val customerService: CustomerService) {

    @GetMapping
    fun getUsers():List<Customer> {
      return customerService.getCustomers()
    }

    @PutMapping
    fun saveUser(@RequestBody customer: Customer) {
        val customer = Customer()
        // userService.createUser(user)
    }

}