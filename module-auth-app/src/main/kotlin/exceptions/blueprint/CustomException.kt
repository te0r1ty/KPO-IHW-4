package com.example.authservice.exceptions.blueprint

import org.springframework.http.HttpStatus

class CustomException(
    val type : String,
    val msg : String,
    val http : HttpStatus
) : RuntimeException()