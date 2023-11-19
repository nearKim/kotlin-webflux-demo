package com.example.webfluxdemo.config

import com.example.webfluxdemo.dto.MultiplyRequestDto
import com.example.webfluxdemo.dto.Response
import com.example.webfluxdemo.exception.InputFailedException
import com.example.webfluxdemo.service.ReactiveMathService
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class RequestHandler(val mathService: ReactiveMathService) {
    fun squareHandler(request: ServerRequest): Mono<ServerResponse> {
        val input = request.pathVariable("input").toInt()
        val responseMono = mathService.findSquare(input)
        return ServerResponse.ok().body(responseMono, Response::class.java)
    }

    fun tableHandler(request: ServerRequest): Mono<ServerResponse> {
        val input = request.pathVariable("input").toInt()
        val responseFlux = mathService.multiplicationTable(input)
        return ServerResponse.ok().body(responseFlux, Response::class.java)
    }

    fun tableStreamHandler(request: ServerRequest): Mono<ServerResponse> {
        val input = request.pathVariable("input").toInt()
        val responseFlux = mathService.multiplicationTable(input)
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, Response::class.java)
    }

    fun multiplyHandler(request: ServerRequest): Mono<ServerResponse> {
        val requestDto = request.bodyToMono(MultiplyRequestDto::class.java)
        val responseMono = this.mathService.multiply(requestDto)
        return ServerResponse.ok()
                .body(responseMono, Response::class.java)
    }

    fun squareHandlerWithValidation(request: ServerRequest): Mono<ServerResponse> {
        val input = Integer.parseInt(request.pathVariable("input"));
        if (input < 10 || input > 20) {
            return Mono.error(InputFailedException(input))
        }
        val responseMono = mathService.findSquare(input)
        return ServerResponse.ok().body(responseMono, Response::class.java)
    }
}