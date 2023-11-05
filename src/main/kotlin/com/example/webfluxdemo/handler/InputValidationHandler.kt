package com.example.webfluxdemo.handler

import com.example.webfluxdemo.dto.InputFailedValidationResponse
import com.example.webfluxdemo.exception.InputFailedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class InputValidationHandler {
    @ExceptionHandler(InputFailedException::class)
    fun handleInputFailedException(ex: InputFailedException): ResponseEntity<InputFailedValidationResponse> {
        val response = InputFailedValidationResponse(
            errorCode = 400,
            input = ex.getInput(),
            message = ex.message!!
        )
        return ResponseEntity.badRequest().body(response)
    }
}