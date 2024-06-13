package com.example.authservice.data.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "\"order\"")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int = 0,

    @Column(name = "user_id", nullable = false)
    val userId: Int,

    @Column(name = "from_station_id", nullable = false)
    val fromStationId: Int,

    @Column(name = "to_station_id", nullable = false)
    val toStationId: Int,

    @Column(name = "status")
    var status: Int = 1,

    @Column(name = "created")
    val created: Timestamp = Timestamp(System.currentTimeMillis())
)
