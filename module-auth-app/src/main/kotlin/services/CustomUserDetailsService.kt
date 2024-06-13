package com.example.authservice.services

import com.example.authservice.data.repositories.UserRepository
import com.example.authservice.exceptions.blueprint.CustomException
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw CustomException(
                "Not found",
                "No user found with email: $email",
                HttpStatus.NOT_FOUND
            )

        return User(
            user.email,
            user.password,
            emptyList()
        )
    }
}