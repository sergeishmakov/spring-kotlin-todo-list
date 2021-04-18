package com.services

import com.models.User

interface UserService {
    fun getUsers(): List<User>
    fun createUser(user: User): User
}