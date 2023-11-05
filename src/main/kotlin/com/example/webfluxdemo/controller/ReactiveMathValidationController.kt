package com.example.webfluxdemo.controller

import com.example.webfluxdemo.dto.MultiplyRequestDto
import com.example.webfluxdemo.dto.Response
import com.example.webfluxdemo.exception.InputFailedException
import com.example.webfluxdemo.service.ReactiveMathService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/reactive-math")
class ReactiveMathValidationController(val mathService: ReactiveMathService) {


    @GetMapping("/square/{input}/throw")
    fun findSquare(@PathVariable input: Int): Mono<Response> {
        if (input < 10 || input > 20) {
            throw InputFailedException(input)
        }
        return mathService.findSquare(input)
    }

}