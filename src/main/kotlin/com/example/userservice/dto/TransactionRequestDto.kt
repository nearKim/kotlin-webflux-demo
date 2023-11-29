package com.example.userservice.dto

data class TransactionRequestDto(val userId: Int, val amount: Int, val status: TransactionStatus)
