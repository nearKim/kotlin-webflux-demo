package com.example.userservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

class UserTransaction {
    @Id
    private var id: Int? = null
    private var userId: Int? = null
    private var amount: Int? = null
    private var transactionDate: LocalDateTime? = null
}