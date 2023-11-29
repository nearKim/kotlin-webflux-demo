package com.example.productservice.service

import com.example.productservice.dto.ProductDto
import com.example.productservice.repository.ProductRepository
import com.example.productservice.util.EntityDtoUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProductService(val repository: ProductRepository) {

    fun getAll(): Flux<ProductDto> {
        return repository.findAll()
            .map(EntityDtoUtil::toDto)
    }

    fun getProductByPriceRange(min: Int, max: Int): Flux<ProductDto> {
        return repository.findByPriceBetween(min, max)
            .map(EntityDtoUtil::toDto)
    }

    fun getProductById(id: String): Mono<ProductDto> {
        return repository.findById(id)
            .map(EntityDtoUtil::toDto)
    }

    fun insertProduct(productDtoMono: Mono<ProductDto>): Mono<ProductDto> {
        return productDtoMono
            .map(EntityDtoUtil::toEntity)
            .flatMap(repository::insert)
            .map(EntityDtoUtil::toDto)
    }

    fun updateProduct(id: String, productDtoMono: Mono<ProductDto>): Mono<ProductDto> {
        return repository.findById(id)
            .flatMap { existingProduct ->
                productDtoMono.map(EntityDtoUtil::toEntity)
                    .doOnNext { newProduct ->
                        newProduct.id = existingProduct.id
                    }
            }
            .flatMap(repository::save)
            .map(EntityDtoUtil::toDto)
    }

    fun deleteProduct(id: String): Mono<Void> {
        return repository.deleteById(id)
    }
}