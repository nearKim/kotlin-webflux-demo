package com.example.webfluxdemo

import com.example.webfluxdemo.dto.Response
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.reactive.function.client.WebClient
import reactor.test.StepVerifier

class GetMultiResponseTest : BaseTest() {

    @Autowired
    private lateinit var webClient: WebClient


    @Test
    fun fluxTest() {
        val responseFlux = webClient
            .get()
            .uri("/reactive-math/table/{number}", 5)
            .retrieve()
            .bodyToFlux(Response::class.java)
            .doOnNext { println(it) }

        StepVerifier.create(responseFlux)
            .expectNextCount(10)
            .verifyComplete()
    }

    @Test
    fun fluxStreamTest() {
        val responseFlux = webClient
            .get()
            .uri("/router/table/{number}/stream", 5)
            .retrieve()
            .bodyToFlux(Response::class.java)
            .doOnNext { println(it) }

        StepVerifier.create(responseFlux)
            .expectNextCount(10)
            .verifyComplete()
    }
}
