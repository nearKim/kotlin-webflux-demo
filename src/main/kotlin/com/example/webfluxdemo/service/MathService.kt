package com.example.webfluxdemo.service

import com.example.webfluxdemo.SleepUtil
import com.example.webfluxdemo.dto.Response
import org.springframework.stereotype.Service

@Service
class MathService {
    fun findSquare(number: Int): Response {
        return Response(output = number * number)
    }

    fun multiplicationTable(number: Int): List<Response>{
        return (1..10)
            .onEach { SleepUtil.sleepSeconds(it) }
            .onEach { println("MathService Processing...: $it") }
            .map { Response(output = it * number) }
    }
}