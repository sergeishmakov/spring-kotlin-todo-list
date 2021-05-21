package com.models

import javax.persistence.*

@Entity()
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(unique = true)
    val email: String,
    var password: String,
    val role: Role,
    val status: Status,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "user")
//    @JoinColumn(name = "user_id")
    val tasks: List<Task> = emptyList(),
)