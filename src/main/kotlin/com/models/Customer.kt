package com.models

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Customer {
    @Id
    Long id;
    String firstName;
    LocalDate dob;
}