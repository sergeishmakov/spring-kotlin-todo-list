package com.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import com.services.CustomerService
import com.models.Customer

@RestController
@RequestMapping("/customers")
class CustomerController(val customerService: CustomerService) {

    @GetMapping
    fun index(): List<Customer> {
      return customerService.allCustomers()
    }

    @PostMapping
    fun create(@RequestBody customer: Customer): Customer {
      return customerService.addCustomer(customer)
    }
}