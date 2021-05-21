package com.services

import com.dtos.SafeUser
import com.dtos.UpdateUserDto
import com.models.User

interface UserService {
    fun getUsers(): List<User>
    fun getUserByEmail(email: String): User?
    fun createUser(user: User)
    fun getUserById(id: Long): User?
    fun save(user: User): User
    fun convertUserUpdateDtoToUser(user: User, updateData: UpdateUserDto): User
    fun convertToSafeUser(user: User): SafeUser
}