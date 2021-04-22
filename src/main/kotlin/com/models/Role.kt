package com.models

import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors


enum class Role(private val permissions: Set<Permission>) {
    USER(setOf(Permission.USERS_READ, Permission.USERS_WRITE)),
    ADMIN(
        setOf(
            Permission.USERS_READ,
            Permission.USERS_WRITE,
            Permission.ADMINS_READ,
            Permission.ADMINS_WRITE
        )
    );

    val authorities: Set<SimpleGrantedAuthority>
        get() = permissions.stream()
            .map { permission: Permission ->
                SimpleGrantedAuthority(
                    permission.permission
                )
            }
            .collect(Collectors.toSet())

}