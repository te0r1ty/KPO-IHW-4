package com.example.buyservice.data.dto

import java.sql.Timestamp

data class OrderInfoDTO(
    val nickname: String,
    val from: String,
    val to: String,
    val status: String,
    val created: Timestamp
)