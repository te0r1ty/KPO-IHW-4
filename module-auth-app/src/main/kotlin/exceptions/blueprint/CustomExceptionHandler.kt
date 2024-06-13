package com.example.authservice.exceptions.blueprint

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(CustomException::class)
    protected fun handleEmailExistsException(ex: CustomException): ResponseEntity<CustomExceptionJson> {
        return ResponseEntity(
            CustomExceptionJson(
                ex.type,
                ex.msg
            ), ex.http
        )
    }

    @Data
    @AllArgsConstructor
    data class CustomExceptionJson(
        val error: String,
        val message: String
    )
}