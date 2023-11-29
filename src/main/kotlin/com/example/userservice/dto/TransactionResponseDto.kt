package com.example.userservice.dto

data class TransactionResponseDto(val userId: Int, val amount: Int, val status: TransactionStatus)
