package com.example.buyservice.data.repositories

import com.example.buyservice.data.entities.Station
import org.springframework.data.jpa.repository.JpaRepository

interface StationRepository : JpaRepository<Station, Long> {
    fun findByStation(name: String): Station?
    fun findById(id: Int): Station?
}