package com.example.buyservice.services

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import com.example.buyservice.configs.JwtProperties
import java.util.*

@Service
class JwtService(jwtProperties: JwtProperties) {
    private val secretKey = Keys.hmacShaKeyFor(jwtProperties.key.toByteArray())

    fun extractEmail(token: String): String {
        return getAllClaims(token).subject
    }

    private fun extractExpiration(token: String): Date {
        return getAllClaims(token).expiration
    }

    fun isExpired(token: String): Boolean {
        return extractExpiration(token).before(Date(System.currentTimeMillis()))
    }

    fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return email == userDetails.username && !isExpired(token)
    }

    private fun getAllClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }
}