package com.models

enum class Permission(val permission: String) {
    USERS_READ("users:read"), USERS_WRITE("users:write"), ADMINS_READ("admins:read"), ADMINS_WRITE("admins:write");

}