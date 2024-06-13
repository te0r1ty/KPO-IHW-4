package com.example.buyservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BuyApp

fun main(args: Array<String>) {
    runApplication<BuyApp>(*args)
}