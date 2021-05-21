package com.controllers

import com.dtos.SafeUser
import com.dtos.UpdateUserDto
import com.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
@PreAuthorize("hasAuthority('users:read')")
class UsersController(val userService: UserService) {

  @GetMapping()
  fun getUsers():List<SafeUser>{
    return userService.getUsers().map { user ->  userService.convertToSafeUser(user)}
  }

  @PutMapping("{id}")
  fun updateUser(@PathVariable id: Long, @RequestBody body: UpdateUserDto): ResponseEntity<Any> {
    print(body)
    val user = userService.getUserById(id)
      ?: return ResponseEntity("User not found", HttpStatus.NOT_FOUND)
    val newUser = userService.convertUserUpdateDtoToUser(user, body)

    val result = userService.save(newUser)
    return ResponseEntity.ok(result)
  }
}
