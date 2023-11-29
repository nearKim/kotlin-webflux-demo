package com.example.userservice.controller

import com.example.userservice.dto.UserDto
import com.example.userservice.service.UserService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.* // ktlint-disable no-wildcard-imports
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("user")
class UserController(val service: UserService) {

    @GetMapping("all")
    fun all(): Flux<UserDto> {
        return service.all()
    }

    @GetMapping("{id}")
    fun getUserById(@PathVariable id: Int): Mono<ResponseEntity<UserDto>> {
        return service.getUserById(id)
            .map { ResponseEntity<UserDto>(HttpStatusCode.valueOf(200)) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createUser(@RequestBody userDtoMono: Mono<UserDto>): Mono<UserDto> {
        return service.createUser(userDtoMono)
    }

    @PutMapping("{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody userDtoMono: Mono<UserDto>): Mono<ResponseEntity<UserDto>> {
        return service.updateUser(id, userDtoMono)
            .map { ResponseEntity<UserDto>(HttpStatusCode.valueOf(200)) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: Int): Mono<Void> {
        return service.deleteUser(id)
    }
}
