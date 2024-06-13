package com.example.authservice.data.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class AuthRequest(
    @field:NotBlank(message = "Email is mandatory")
    @field:Email(message = "Email should be valid")
    val email: String,

    @field:NotBlank(message = "Password is mandatory")
    val password: String
)