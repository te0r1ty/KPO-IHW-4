package com.example.buyservice.data.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "session")
data class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "user_id", nullable = false)
    val userId: Int,

    @Column(name = "token", nullable = false)
    val token: String,

    @Column(name = "expires")
    val expires: Timestamp = Timestamp(System.currentTimeMillis())
)