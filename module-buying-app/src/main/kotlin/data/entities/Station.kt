package com.example.buyservice.data.entities

import jakarta.persistence.*

@Entity
@Table(name = "station")
data class Station(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "station", nullable = false)
    val station: String
)
