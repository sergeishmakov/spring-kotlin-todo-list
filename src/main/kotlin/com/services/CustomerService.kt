package com.services

import com.models.Customer

interface CustomerService {
    fun getCustomers(): List<Customer>
    fun createCustomer(user: Customer)
}