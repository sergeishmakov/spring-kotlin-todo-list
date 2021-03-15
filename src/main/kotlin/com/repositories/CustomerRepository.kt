package com.repositories

import com.models.Customer
import org.springframework.data.repository

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
    @Query("SELECT * from customers")
    List<Customer> findAll();

    @Query("INSERT INTO customers (name) VALUES (:name)")
    Person create(@Param("name") String name);
}
