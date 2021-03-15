package com.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.sql.ResultSet

import com.models.Customer
 
@Repository
class CustomerRepository(@Autowired var jdbcTemplate: JdbcTemplate) {

    fun create(customer: Customer): Customer {
        SimpleJdbcInsert(jdbcTemplate).withTableName("customers").apply {
            setGeneratedKeyName("id")
            execute(
                    mapOf("id" to null, 
                          "name" to customer.name,
                          "email" to customer.email))
        }
        return customer
    }

    fun findAll(): List<Customer> = jdbcTemplate.query("SELECT * FROM customers",
            { rs: ResultSet, _: Int ->
                Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"))
            })
}