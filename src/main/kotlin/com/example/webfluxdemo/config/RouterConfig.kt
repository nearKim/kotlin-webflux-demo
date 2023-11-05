package com.example.webfluxdemo.config

import com.example.webfluxdemo.exception.InputFailedException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import java.util.function.BiFunction

@Configuration
class RouterConfig(val requestHandler: RequestHandler) {
    @Bean
    fun routerFunctions(): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
                .path("router", ::serverResponseRouterFunction)
                .build()
    }


    private fun serverResponseRouterFunction(): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
                    .GET("square/{input}", RequestPredicates.path("*/1?") , requestHandler::squareHandler)
                    .GET("square/{input}") {
                        ServerResponse.badRequest().bodyValue("Only 10-19 allowed")
                    }
                    .GET("table/{input}", requestHandler:: tableHandler)
                    .GET("table/{input}/stream", requestHandler:: tableStreamHandler)
                    .POST("multiply", requestHandler:: multiplyHandler)
                    .GET("square/{input}/validation", requestHandler::squareHandlerWithValidation)
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