package com.example.webfluxdemo

import com.example.webfluxdemo.dto.Response
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.reactive.function.client.WebClient
import reactor.test.StepVerifier

class GetSingleResponseTest : BaseTest() {

    @Autowired
    private lateinit var webClient: WebClient

    @Test
    fun blockTest() {
        val response = webClient
            .get()
            .uri("/reactive-math/square/{number}", 5)
            .retrieve()
            .bodyToMono(Response::class.java)
            .block()
        println(response?.date)
        println(response?.output)
    }

    @Test
    fun stepVerifierTest() {
        val responseMono = webClient
            .get()
            .uri("/reactive-math/square/{number}", 5)
            .retrieve()
            .bodyToMono(Response::class.java)

        StepVerifier.create(responseMono)
            .expectNextMatches { response ->
                response.output == 25
            }
            .verifyComplete()
    }
}
