package com.models

import javax.persistence.*


@Entity(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(unique = true)
    val email: String,
    var password: String,
    val role: Role,
    val status: Status
)