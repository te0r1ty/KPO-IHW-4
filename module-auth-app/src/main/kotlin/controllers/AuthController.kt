package com.example.authservice.controllers

import com.example.authservice.data.dto.AuthRequest
import com.example.authservice.data.dto.AuthResponse
import com.example.authservice.services.AuthService
import com.example.authservice.services.SessionService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
@Validated
class AuthController(
    private val authService: AuthService,
    private val sessionService: SessionService
) {
    @PostMapping("/login")
    fun authenticate(@Valid @RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        val token = authService.createToken(authRequest)
        sessionService.createSession(token.jwt, authRequest)
        return ResponseEntity.ok(token)
    }

    @PostMapping("/logout")
    fun logout(
        @RequestHeader("Authorization") token: String,
    ): ResponseEntity<Void> {
        sessionService.validateSession(token)
        sessionService.deleteSession()
        return ResponseEntity.noContent().build()
    }
}