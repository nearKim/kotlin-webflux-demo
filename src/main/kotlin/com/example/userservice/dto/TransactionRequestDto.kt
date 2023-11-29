package com.example.userservice.dto

data class TransactionRequestDto(val userID: Int, val amount: Int, val status: TransactionStatus)
