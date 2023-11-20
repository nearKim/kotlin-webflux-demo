package com.example.webfluxdemo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.test.StepVerifier

class QueryParamsTest : BaseTest() {

    @Autowired
    private lateinit var webClient: WebClient
    private val queryString = "http://localhost:8081/jobs/search?count={count}&page={page}"

    @Test
    fun queryParamsTest() {
        val uri = UriComponentsBuilder.fromUriString(queryString)
            .build(10, 20)
        val integerFlux = webClient
            .get()
            .uri(uri)
            .retrieve()
            .bodyToFlux(Integer::class.java)
            .doOnNext(::println)

        StepVerifier.create(integerFlux)
            .expectNextCount(2)
            .verifyComplete()
    }

    @Test
    fun queryParamsTest2() {
        val integerFlux = webClient
            .get()
            .uri { it.path("jobs/search").query("count={count}&page={page}").build(10, 20) }
            .retrieve()
            .bodyToFlux(Integer::class.java)
            .doOnNext(::println)

        StepVerifier.create(integerFlux)
            .expectNextCount(2)
            .verifyComplete()
    }

    @Test
    fun queryParamsTest3() {
        val queryParams = mapOf("count" to 10, "page" to 20)
        val integerFlux = webClient
            .get()
            .uri { it.path("jobs/search").query("count={count}&page={page}").build(queryParams) }
            .retrieve()
            .bodyToFlux(Integer::class.java)
            .doOnNext(::println)

        StepVerifier.create(integerFlux)
            .expectNextCount(2)
            .verifyComplete()
    }
}
