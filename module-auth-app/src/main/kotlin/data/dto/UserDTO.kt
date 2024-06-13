package com.example.authservice.data.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import lombok.Builder
import lombok.Data


@Data
@Builder
data class UserDTO(
    @field:NotBlank(message = "Nickname is mandatory")
    @field:Size(max = 50, message = "Nickname must have length less than 50 characters")
    val nickname: String,

    @field:NotBlank(message = "Email is mandatory")
    @field:Size(max = 100, message = "Email must have length less than 100 characters")
    @field:Email(message = "Email should be valid")
    val email: String,

    @field:NotBlank(message = "Password is mandatory")
    @field:Size(min = 8, max = 255, message = "Password should be between 8 and 255 characters long")
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    val password: String,
)