package com.example.webfluxdemo.exception


class InputFailedException(
        val input: Int,
        val errorCode: Int = 100,
        message: String? = "Allowed range is 10 to 20"
): RuntimeException(message)