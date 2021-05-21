package com.services.impl

import com.dtos.SafeUser
import com.dtos.UpdateUserDto
import org.springframework.stereotype.Service
import com.models.User
import com.repositories.UserRepository
import com.services.UserService
import org.springframework.web.client.HttpClientErrorException
import java.util.*

@Service
class UserServiceImpl(
    val userRepository: UserRepository
) : UserService {
    override fun getUsers() = userRepository.findAll()

    override fun getUserByEmail(email: String) = userRepository.findByEmail(email)

    override fun createUser(user: User) {
        userRepository.save(user)
    }

    override fun getUserById(id: Long): User? {
        return userRepository.findById(id).orElseGet(null)
    }

    override fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun convertUserUpdateDtoToUser(user: User, data: UpdateUserDto): User {
        val newEmail = data.email ?: user.email
        val newRole = data.role ?: user.role
        val newStatus = data.status ?: user.status
        val newTasks = data.tasks ?: user.tasks
        val newUser = User(user.id, newEmail, user.password, newRole, newStatus, newTasks)
        return newUser
    }

    override fun convertToSafeUser(user: User): SafeUser {
        return SafeUser(user.id, user.email, user.role, user.status)
    }
}