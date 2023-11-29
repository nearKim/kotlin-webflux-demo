package com.example.userservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
class User {
    @Id
    var id: Int? = null
    var name: String = ""
    var balance: Int = 0
}