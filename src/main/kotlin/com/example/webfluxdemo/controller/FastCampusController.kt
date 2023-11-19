package com.example.webfluxdemo.controller

import com.example.webfluxdemo.service.MathService
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebSession
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/fastcampus")
class FastCampusController() {

    @GetMapping("/swe")
    fun serverWebExchange(serverWebExchange: ServerWebExchange):Mono<String> {
        val name = serverWebExchange.request.queryParams.getFirst("name") ?: "World"
        return Mono.just("Hello $name")
    }

    @GetMapping("/reqres")
    fun serverReqRes(serverHttpRequest: ServerHttpRequest, serverWebResponse: ServerHttpResponse): Mono<String> {
        val name = serverHttpRequest.queryParams.getFirst("name") ?: "World"
        serverWebResponse.statusCode = HttpStatus.CREATED
        return Mono.just("Hello $name")
    }

    @GetMapping("/session")
    fun session(webSession: WebSession, serverWebExchange: ServerWebExchange): Mono<String> {
        val savedName = webSession.getAttribute<String>("name")
        val name = savedName ?: serverWebExchange.request.queryParams.getFirst("name") ?: "World"
        webSession.attributes["name"] = name
        return Mono.just("Hello $name")
    }

}