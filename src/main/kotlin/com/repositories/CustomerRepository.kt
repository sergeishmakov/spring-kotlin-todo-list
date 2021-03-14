package com.repositories

import com.models.Customer

interface CustomerRepository extends CrudRepository<Customer, Long> {}
