package com.example.userservice.controller

import com.example.userservice.dto.TransactionRequestDto
import com.example.userservice.dto.TransactionResponseDto
import com.example.userservice.service.TransactionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("user/transaction")
class UserTransactionController(val transactionService: TransactionService) {

    @PostMapping
    fun createTransaction(@RequestBody requestDtoMono: Mono<TransactionRequestDto>): Mono<TransactionResponseDto> {
        return requestDtoMono.flatMap(transactionService::createTransaction)
    }
}