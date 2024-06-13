package com.example.authservice.controllers

import com.example.authservice.data.dto.UserInfoDTO
import com.example.authservice.services.SessionService
import com.example.authservice.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")
class UserController(
    val sessionService: SessionService,
    val userService: UserService
) {
    @GetMapping("/info")
    fun getUser(@RequestHeader("Authorization") token: String): ResponseEntity<Pair<UserInfoDTO, List<Int>>> {
        sessionService.validateSession(token)
        val res = userService.getInfo()
        return ResponseEntity.ok(res)
    }
}