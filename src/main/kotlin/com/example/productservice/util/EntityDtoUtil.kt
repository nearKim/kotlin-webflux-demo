package com.example.productservice.util

import com.example.productservice.dto.ProductDto
import com.example.productservice.entity.Product

class EntityDtoUtil {
    companion object {
        fun toDto(entity: Product): ProductDto {
            return ProductDto(entity.id!!, entity.description!!, entity.price)
        }

        fun toEntity(dto: ProductDto): Product {
            val entity = Product()
            entity.id = dto.id
            entity.description = dto.description
            entity.price = dto.price
            return entity
        }
    }
}