package com.example.webfluxdemo

import com.example.webfluxdemo.dto.Response
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.test.StepVerifier

class BadRequestTest : BaseTest() {

    @Autowired
    private lateinit var webClient: WebClient

    @Test
    fun badRequestTest() {
        val response = webClient.get()
            .uri("/reactive-math/square/{number}/throw", 5)
            .retrieve()
            .bodyToMono(Response::class.java)
            .doOnNext { println(it) }
            .doOnError { println(it) }

        StepVerifier.create(response).verifyError(WebClientResponseException.BadRequest::class.java)
    }
}
