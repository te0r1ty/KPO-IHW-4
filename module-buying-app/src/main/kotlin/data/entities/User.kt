package com.example.buyservice.data.entities

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import java.sql.Timestamp

@Data
@Entity
@Table(name = "\"user\"")
@Builder
data class User(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false, name = "nickname", length = 50)
    val nickname: String,

    @Column(nullable = false, name = "email", length = 100)
    val email: String,

    @Column(nullable = false, name = "password", length = 255)
    val password: String,

    @Column(name = "created")
    val created: Timestamp = Timestamp(System.currentTimeMillis())
)