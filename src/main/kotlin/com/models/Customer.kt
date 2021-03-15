package com.models

import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Customer( 
    @Id
    val id: Int,
    val name: String = "",
    val email: String = "")