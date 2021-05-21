package com.dtos

import com.models.Role
import com.models.Status

class SafeUser(val id: Long, val email: String, val role: Role, val status: Status)