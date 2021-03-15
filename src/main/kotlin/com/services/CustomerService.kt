package com.services

import org.springframework.stereotype.Service
import com.models.Customer
import com.repositories.CustomerRepository

@Service
class CustomerService(val customerRepository: CustomerRepository) {

    fun addCustomer(customer: Customer): Customer {
        return customerRepository.create(customer)
    }

    fun allCustomers(): List<Customer> {
        return customerRepository.findAll()
    }
}