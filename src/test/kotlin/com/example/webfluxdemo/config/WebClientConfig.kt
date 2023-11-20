package com.example.webfluxdemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Configuration
class WebClientConfig {
    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl("http://localhost:8081")
            .filter(this::sessionToken)
            .build()
    }

    private fun sessionToken(request: ClientRequest, ex: ExchangeFunction): Mono<ClientResponse> {
        println("sessionToken() called")
        val clientRequest = ClientRequest
            .from(request)
            .headers { it.setBearerAuth("some-token")}
            .build()
        return ex.exchange(clientRequest)
    }
}