package com.example.buyservice.controllers

import com.example.buyservice.data.dto.OrderDTO
import com.example.buyservice.data.dto.OrderInfoDTO
import com.example.buyservice.services.OrderService
import com.example.buyservice.services.SessionService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
@Validated
class OrderController(
    val sessionService: SessionService,
    val orderService: OrderService
) {
    @PostMapping("/create")
    fun createOrder(
        @RequestHeader("Authorization", required = false) token: String,
        @Valid @RequestBody order: OrderDTO
    ): ResponseEntity<String> {
        sessionService.validateSession(token)
        orderService.createOrder(token, order)
        return ResponseEntity.ok("Order created successfully")
    }

    @PutMapping("/processing")
    fun processOrders(
        @RequestHeader("Authorization", required = false) token: String,
    ): ResponseEntity<String> {
        sessionService.validateAdminSession(token)
        orderService.processOrders()
        return ResponseEntity.ok("All orders awaiting processing have been successfully completed")
    }

    @GetMapping("/info/{id}")
    fun getOrderInfo(
        @RequestHeader("Authorization", required = false) token: String,
        @PathVariable id: Int
    ): ResponseEntity<OrderInfoDTO> {
        sessionService.validateSession(token)
        val res = orderService.getInfo(id, token)
        return ResponseEntity.ok(res)
    }
}