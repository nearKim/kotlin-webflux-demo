package com.example.webfluxdemo

class SleepUtil {
    companion object {
        fun sleepSeconds(seconds: Int) = Thread.sleep((seconds * 1000).toLong())
    }
}