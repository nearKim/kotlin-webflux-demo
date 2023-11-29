package com.example.userservice.service

import com.example.userservice.dto.UserDto
import com.example.userservice.repository.UserRepository
import com.example.userservice.util.EntityDtoUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(val userRepository: UserRepository) {
    fun all(): Flux<UserDto> = userRepository.findAll().map(EntityDtoUtil::toDto)

    fun getUserById(id: Int): Mono<UserDto> = userRepository.findById(id).map(EntityDtoUtil::toDto)

    fun createUser(userDtoMono: Mono<UserDto>): Mono<UserDto> = userDtoMono
        .map(EntityDtoUtil::toEntity)
        .flatMap(userRepository::save)
        .map(EntityDtoUtil::toDto)

    fun updateUser(id: Int, userDtoMono: Mono<UserDto>): Mono<UserDto> =
        userRepository.findById(id)
            .flatMap {
                userDtoMono
                    .map(EntityDtoUtil::toEntity)
                    .doOnNext { it.id = id }
            }
            .flatMap(userRepository::save)
            .map(EntityDtoUtil::toDto)

    fun deleteUser(id: Int): Mono<Void> = userRepository.deleteById(id)
}
