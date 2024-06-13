package com.example.authservice.data.dto

import java.sql.Timestamp

data class UserInfoDTO(
    val nickname: String,
    val email: String,
    val created: Timestamp,
)