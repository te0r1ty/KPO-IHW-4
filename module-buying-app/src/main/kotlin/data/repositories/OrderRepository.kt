package com.example.buyservice.data.repositories

import com.example.buyservice.data.entities.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun findById(id: Int): Order?
}