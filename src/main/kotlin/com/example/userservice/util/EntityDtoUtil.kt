package com.example.userservice.util

import com.example.userservice.dto.TransactionRequestDto
import com.example.userservice.dto.TransactionResponseDto
import com.example.userservice.dto.TransactionStatus
import com.example.userservice.dto.UserDto
import com.example.userservice.entity.User
import com.example.userservice.entity.UserTransaction
import java.time.LocalDateTime

class EntityDtoUtil {
    companion object {
        fun toDto(user: User): UserDto {
            return UserDto(user.id, user.name, user.balance)
        }

        fun toEntity(userDto: UserDto): User {
            val user = User()
            user.id = userDto.id
            user.name = userDto.name
            user.balance = userDto.balance
            return user
        }

        fun toEntity(requestDto: TransactionRequestDto): UserTransaction {
            val userTransaction = UserTransaction()
            userTransaction.userId = requestDto.userId
            userTransaction.amount = requestDto.amount
            userTransaction.transactionDate = LocalDateTime.now()
            return userTransaction
        }

        fun toDto(requestDto: TransactionRequestDto, status: TransactionStatus): TransactionResponseDto {
            return TransactionResponseDto(requestDto.userId, requestDto.amount, status)
        }
    }
}