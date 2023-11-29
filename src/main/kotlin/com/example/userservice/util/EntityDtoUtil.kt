package com.example.userservice.util

import com.example.userservice.dto.UserDto
import com.example.userservice.entity.User

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

    }
}