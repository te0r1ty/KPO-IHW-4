package com.example.buyservice.data.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class OrderDTO (
    @field:NotBlank(message = "Starter station is mandatory")
    @field:Size(max = 50, message = "Station name must have length less than 50 characters")
    @field:Pattern(regexp = "^[a-zA-Z ]+$", message = "Station name must contain only english letters")
    val from: String,

    @field:NotBlank(message = "Destination station is mandatory")
    @field:Size(max = 50, message = "Station name must have length less than 50 characters")
    @field:Pattern(regexp = "^[a-zA-Z ]+$", message = "Station name must contain only english letters")
    val to: String,
)