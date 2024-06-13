package com.example.authservice.controllers

import com.example.authservice.data.dto.UserDTO
import com.example.authservice.services.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/signup")
@Validated
class RegistrationController(
    val userService: UserService
) {
    @PostMapping
    fun signup(
        @Valid @RequestBody user: UserDTO
    ): ResponseEntity<String> {
        val userEnt = userService.registration(user)
        return ResponseEntity("User registered successfully with nickname: ${userEnt.nickname}", HttpStatus.CREATED)
    }
}