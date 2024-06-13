package com.example.authservice.data.repositories

import com.example.authservice.data.entities.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun findByUserId(userId: Int): List<Order>?
}