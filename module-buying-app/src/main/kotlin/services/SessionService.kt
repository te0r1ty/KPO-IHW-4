package com.example.buyservice.services

import com.example.buyservice.data.repositories.SessionRepository
import com.example.buyservice.exceptions.blueprint.CustomException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class SessionService(
    private val sessionRepository: SessionRepository,
) {
    fun validateSession(token: String) {
        if (sessionRepository.findByToken(token.substring(7)) == null)
            throw CustomException(
                "Unauthorized",
                "Login to access this resource",
                HttpStatus.UNAUTHORIZED
            )

        return
    }

    fun validateAdminSession(token: String) {
        if (sessionRepository.findByToken(token.substring(7))!!.userId != 0)
            throw CustomException(
                "Forbidden",
                "Admin access only",
                HttpStatus.FORBIDDEN
            )

        return
    }
}