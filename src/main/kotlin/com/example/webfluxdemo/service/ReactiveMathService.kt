package com.example.webfluxdemo.service

import com.example.webfluxdemo.SleepUtil
import com.example.webfluxdemo.dto.MultiplyRequestDto
import com.example.webfluxdemo.dto.Response
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@Service
class ReactiveMathService {
    fun findSquare(number: Int): Mono<Response> {
        return Mono.fromSupplier { number * number }
            .map { Response(output = it) }
    }

    fun multiplicationTable(number: Int): Flux<Response> {
        return Flux.range(1, 10)
//            .delayElements(Duration.ofSeconds(1))
            .doOnNext { SleepUtil.sleepSeconds(1) }
            .doOnNext { println("MathService Processing...: $it") }
            .map { Response(output = it * number) }
    }

    fun multiply(request: Mono<MultiplyRequestDto>): Mono<Response> {
        return request
            .map { it.first * it.second }
            .map { Response(output = it) }
    }
}