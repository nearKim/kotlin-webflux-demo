package com.example.webfluxdemo

import com.example.webfluxdemo.dto.InputFailedValidationResponse
import com.example.webfluxdemo.dto.Response
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class ExchangeTest : BaseTest() {

    @Autowired
    private lateinit var webClient: WebClient

    @Test
    fun exchangeTest() {
        val response = webClient.get()
            .uri("/reactive-math/square/{number}/throw", 5)
            .exchangeToMono { response -> exchange(response) }
            .doOnNext { println(it) }
            .doOnError { println(it) }

        StepVerifier.create(response).expectNextCount(1).verifyComplete()
    }

    private fun exchange(cr: ClientResponse): Mono<Any> {
        return if (cr.statusCode().is4xxClientError) {
            cr.bodyToMono(InputFailedValidationResponse::class.java)
        } else {
            cr.bodyToMono(Response::class.java)
        }
    }
}
