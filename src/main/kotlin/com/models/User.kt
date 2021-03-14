package com.models

import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Table(name="users")
class User (
    @Id
    val id: Int,
    val name: String,
    val email: String
)