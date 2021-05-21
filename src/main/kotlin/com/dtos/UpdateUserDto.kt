package com.dtos

import com.models.Role
import com.models.Status
import com.models.Task


class UpdateUserDto (
    val email: String?,
    val role: Role?,
    val status: Status?,
    val tasks: List<Task> = emptyList()
    )