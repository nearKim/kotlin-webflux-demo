package com.example.webfluxdemo.exception


class InputFailedException(private val input: Int, message: String? = "Allowed range is 10 to 20"): RuntimeException(message){
    companion object {
        private val errorCode = 100
    }
    fun getInput(): Int {
        return input
    }
}