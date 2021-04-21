package com.security

import com.models.Status
import com.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class SecurityUser(
    private val username: String,
    private val password: String,
    private val authorities: List<SimpleGrantedAuthority?>,
    private val isActive: Boolean
) :
    UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return isActive
    }

    override fun isAccountNonLocked(): Boolean {
        return isActive
    }

    override fun isCredentialsNonExpired(): Boolean {
        return isActive
    }

    override fun isEnabled(): Boolean {
        return isActive
    }

    companion object {
        fun fromUser(user: User): UserDetails {
            return org.springframework.security.core.userdetails.User(
                user.email,
                user.password,
                user.status == Status.ACTIVE,
                user.status == Status.ACTIVE,
                user.status == Status.ACTIVE,
                user.status == Status.ACTIVE,
                user.role.authorities
            )
        }
    }
}