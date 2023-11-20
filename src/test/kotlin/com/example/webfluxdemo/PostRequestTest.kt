package com.example.webfluxdemo

import com.example.webfluxdemo.dto.MultiplyRequestDto
import com.example.webfluxdemo.dto.Response
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.reactive.function.client.WebClient
import reactor.test.StepVerifier

class PostRequestTest : BaseTest() {

    @Autowired
    private lateinit var webClient: WebClient

    @Test
    fun postTest() {
        val response = webClient.post()
            .uri("/reactive-math/multiply")
            .bodyValue(buildRequestDto(5, 2))
            .retrieve()
            .bodyToMono(Response::class.java)
            .doOnNext { println(it) }

        StepVerifier.create(response).expectNextCount(1).verifyComplete()
    }

    private fun buildRequestDto(a: Int, b: Int): MultiplyRequestDto {
        return MultiplyRequestDto(a, b)
    }
}
