package com.repositories

import com.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    override fun findById(id: Long): Optional<User>
}