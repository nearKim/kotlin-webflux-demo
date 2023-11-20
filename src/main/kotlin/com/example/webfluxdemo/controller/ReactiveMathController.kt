package com.example.webfluxdemo.controller

import com.example.webfluxdemo.dto.MultiplyRequestDto
import com.example.webfluxdemo.dto.Response
import com.example.webfluxdemo.service.ReactiveMathService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/reactive-math")
class ReactiveMathController(val mathService: ReactiveMathService) {


    @GetMapping("/square/{input}")
    fun findSquare(@PathVariable input: Int): Mono<Response> = mathService.findSquare(input)

    @GetMapping("/table/{input}")
    fun multiplicationTable(@PathVariable input: Int): Flux<Response> = mathService.multiplicationTable(input)

    @GetMapping("/table/{input}/stream", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun multiplicationTableStream(@PathVariable input: Int): Flux<Response> = mathService.multiplicationTable(input)

    @PostMapping("/multiply")
    fun multiply(@RequestBody multiplyRequestDto: Mono<MultiplyRequestDto>, @RequestHeader headers: HttpHeaders): Mono<Response> =
        mathService.multiply(multiplyRequestDto)
}