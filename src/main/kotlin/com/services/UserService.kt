package com.services

import com.models.User

interface UserService {
    fun getUsers(): List<User>
    fun getUserByEmail(email: String): User?
    fun createUser(user: User)
}