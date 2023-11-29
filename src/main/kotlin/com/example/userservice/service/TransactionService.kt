package com.example.userservice.service

import com.example.userservice.util.EntityDtoUtil
import com.example.userservice.dto.TransactionRequestDto
import com.example.userservice.dto.TransactionResponseDto
import com.example.userservice.dto.TransactionStatus
import com.example.userservice.repository.UserRepository
import com.example.userservice.repository.UserTransactionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TransactionService(val userRepository: UserRepository, val userTransactionRepository: UserTransactionRepository) {

    fun createTransaction(requestDto: TransactionRequestDto): Mono<TransactionResponseDto> {
        return userRepository.updateUserByBalance(requestDto.userId, requestDto.amount)
                .filter { it }
                .map { EntityDtoUtil.toEntity(requestDto) }
                .flatMap { userTransactionRepository.save(it) }
                .map { EntityDtoUtil.toDto(requestDto, TransactionStatus.APPROVED) }
                .defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED))
    }

}