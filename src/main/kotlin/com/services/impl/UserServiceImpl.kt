package com.services.impl

import org.springframework.stereotype.Service
import com.models.User
import com.repositories.UserRepository
import com.services.UserService

@Service
class UserServiceImpl(
    val userRepository: UserRepository
) : UserService {
    override fun getUsers() = userRepository.findAll()

    override fun getUserByEmail(email: String) = userRepository.findByEmail(email)

    override fun createUser(user: User) {
        userRepository.save(user)
    }
}