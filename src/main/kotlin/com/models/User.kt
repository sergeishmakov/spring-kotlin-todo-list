package com.models

import com.constants.Roles
import javax.persistence.*


@Entity(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    @Column(unique = true)
    val email: String,
    var password: String,
    val role: String
)