package com.example.buyservice.data.repositories

import com.example.buyservice.data.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findById(id: Int): User?
    fun findByEmail(email: String): User?
}
