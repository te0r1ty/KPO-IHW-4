package com.example.authservice.services

import com.example.authservice.data.dto.AuthRequest
import com.example.authservice.data.entities.Session
import com.example.authservice.data.repositories.SessionRepository
import com.example.authservice.data.repositories.UserRepository
import com.example.authservice.exceptions.blueprint.CustomException
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SessionService(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository
) {
    fun createSession(
        token: String,
        authRequest: AuthRequest
    ): String {
        val session = Session(
            userId = userRepository.findByEmail(authRequest.email)!!.id,
            token = token
        )

        sessionRepository.save(session)

        return token
    }

    fun validateSession(token: String) {
        if (sessionRepository.findByToken(token.substring(7)) == null)
            throw CustomException(
                "Unauthorized",
                "Login to access this resource",
                HttpStatus.UNAUTHORIZED
            )

        return
    }

    @Transactional
    fun deleteSession() {
        val id = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)!!.id
        sessionRepository.deleteByUserId(id)

        return
    }
}