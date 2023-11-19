package com.example.webfluxdemo.controller

import com.example.webfluxdemo.dto.MultiplyRequestDto
import com.example.webfluxdemo.dto.Response
import com.example.webfluxdemo.exception.InputFailedException
import com.example.webfluxdemo.service.ReactiveMathService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
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

    @GetMapping("/square/{input}/mono-error")
    fun monoError(@PathVariable input: Int): Mono<Response> {
        return Mono.just(input).handle { t, u ->
            if (t in 10..20) {
                u.next(t)
            } else {
                u.error(InputFailedException(t))
            }
        }.cast(Int::class.java)
                .flatMap { mathService.findSquare(it) }
    }

    @GetMapping("/square/{input}/assignment")
    fun assignment(@PathVariable input: Int): Mono<ResponseEntity<Response>> {
        return Mono.just(input)
                .filter { it in 10..20 }
                .flatMap { mathService.findSquare(it) }
                .map { ResponseEntity.ok(it) }
                .defaultIfEmpty(ResponseEntity.badRequest().build())

    }
}