package com.example.productservice.repository

import com.example.productservice.entity.Product
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ProductRepository : ReactiveMongoRepository<Product, String> {
    fun findByPriceBetween(min: Int, max: Int): Flux<Product>
}