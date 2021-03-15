package com.services.impl

import org.springframework.stereotype.Service
import com.models.Customer
import com.repositories.CustomerRepository
import com.services.CustomerService

@Service
class CustomerServiceImpl(
    val customerRepository: CustomerRepository
) : CustomerService {
    override fun getCustomers() = customerRepository.findAll()

    override fun createCustomer(customer: Customer) {
        customerRepository.create(customer)
    }
}