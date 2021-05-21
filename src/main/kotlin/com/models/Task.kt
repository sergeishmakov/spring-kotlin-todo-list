package com.models

import javax.persistence.*

@Entity
@Table(name = "tasks")
class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    val user: User
)
