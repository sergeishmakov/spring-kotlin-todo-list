package com.models

import com.constants.Roles
import javax.persistence.*


@Entity
@Table(name="users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val email: String,
    var password: String,
    val role: Roles
)