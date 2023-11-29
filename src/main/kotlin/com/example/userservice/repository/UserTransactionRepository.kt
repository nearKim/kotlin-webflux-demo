package com.example.userservice.repository

import com.example.userservice.entity.UserTransaction
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserTransactionRepository : ReactiveCrudRepository<UserTransaction, Int>
