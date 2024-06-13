package com.example.authservice.data.repositories

import com.example.authservice.data.entities.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Int> {
    fun findByToken(token: String): Session?
    fun deleteByUserId(id: Int)
}