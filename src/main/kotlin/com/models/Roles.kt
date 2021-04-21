package com.models

import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors


enum class Role(private val permissions: Set<Permission>) {
    USER(setOf(Permission.DEVELOPERS_READ)), ADMIN(
        setOf(
            Permission.DEVELOPERS_READ,
            Permission.DEVELOPERS_WRITE
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