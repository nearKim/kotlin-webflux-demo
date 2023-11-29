package com.example.userservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

class UserTransaction {
    @Id
    val id: Int? = null
    var userId: Int? = null
    var amount: Int = 0
    var transactionDate: LocalDateTime? = null
}