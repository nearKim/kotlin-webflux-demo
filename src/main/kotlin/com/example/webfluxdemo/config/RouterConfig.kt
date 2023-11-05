package com.example.webfluxdemo.config

import com.example.webfluxdemo.exception.InputFailedException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.function.BiFunction

@Configuration
class RouterConfig(val requestHandler: RequestHandler) {
    @Bean
    fun serverResponseRouterFunction(): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
                    .GET("router/square/{input}", requestHandler::squareHandler)
                    .GET("router/table/{input}", requestHandler:: tableHandler)
                    .GET("router/table/{input}/stream", requestHandler:: tableStreamHandler)
                    .POST("router/multiply", requestHandler:: multiplyHandler)
                .onError(InputFailedException::class.java, exceptionHandler())
                 .build()

    }
}

fun handle(err: Throwable, req: ServerRequest): Mono<ServerResponse> {
    val ex = err as InputFailedException
    val response = InputFailedException(input=ex.input, message = ex.message, errorCode = ex.errorCode)
    return ServerResponse.badRequest().bodyValue(response)
}
private fun exceptionHandler(): BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> = BiFunction(::handle)