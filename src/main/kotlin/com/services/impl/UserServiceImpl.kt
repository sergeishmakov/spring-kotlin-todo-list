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

    override fun createUser(user: User):User {
        userRepository.save(user)
        return user
    }
}