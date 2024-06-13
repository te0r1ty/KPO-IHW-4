package com.example.buyservice.data.repositories

import com.example.buyservice.data.entities.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Int> {
    fun findByToken(token: String): Session?
}