package com.controllers

// import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class UsersController {

  @GetMapping
  fun index(): String {
    return "Hello world"
  }

  // @PostMapping // Экшен принимает POST запрос без параметров в url
  // @ResponseStatus(HttpStatus.CREATED) // Указываем специфический HttpStatus при успешном ответе
  // fun create(@RequestBody product: Product) = productService.add(product) 

}