package com.example.authservice.services

import com.example.authservice.configs.JwtProperties
import com.example.authservice.data.dto.AuthRequest
import com.example.authservice.data.dto.AuthResponse
import com.example.authservice.data.repositories.UserRepository
import com.example.authservice.exceptions.blueprint.CustomException
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService,
    private val jwtProperties: JwtProperties,
    private val userRepository: UserRepository,
) {
    fun createToken(authRequest: AuthRequest): AuthResponse {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(authRequest.email, authRequest.password)
            )
        } catch (e: Exception) {
            if (userRepository.findByEmail(authRequest.email) == null) throw CustomException(
                "Unauthorised", "No user found with email ${authRequest.email}", HttpStatus.UNAUTHORIZED
            )
            else throw CustomException(
                "Unauthorised", "Wrong password", HttpStatus.UNAUTHORIZED
            )
        }

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authRequest.email)
        val jwt: String = jwtService.generateToken(
            userDetails,
            Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )
        return AuthResponse(jwt)
    }
}