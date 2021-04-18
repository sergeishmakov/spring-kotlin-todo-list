package com.models

import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Id

//import javax.validation.constraints.Email
//import javax.validation.constraints.NotNull

@Entity
@Table(name="users")
class User (
    @Id
    val id: Int,
    val name: String,
    @NotNull(message = "Email should be valid")
    val email: String,
    val password: String,
)