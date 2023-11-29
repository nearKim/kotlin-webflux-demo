package com.example.productservice.controller

import com.example.productservice.dto.ProductDto
import com.example.productservice.service.ProductService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("product")
class ProductController(val service: ProductService) {

    @GetMapping("all")
    fun all(): Flux<ProductDto> {
        return service.getAll()
    }

    @GetMapping("price-range")
    fun getByPriceRange(@RequestParam("min") min: Int, @RequestParam("max") max: Int): Flux<ProductDto> {
        return service.getProductByPriceRange(min, max)
    }
    @GetMapping("{id}")
    fun getProductById(id: String): Mono<ResponseEntity<ProductDto>> {
        return service.getProductById(id)
            .map { ResponseEntity<ProductDto>(HttpStatusCode.valueOf(200)) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun insertProduct(@RequestBody productDtoMono: Mono<ProductDto>): Mono<ProductDto> {
        return service.insertProduct(productDtoMono)
    }

    @PutMapping("{id}")
    fun updateProduct(@PathVariable id: String, @RequestBody productDtoMono: Mono<ProductDto>): Mono<ResponseEntity<ProductDto>> {
        return service.updateProduct(id, productDtoMono)
            .map { ResponseEntity<ProductDto>(HttpStatusCode.valueOf(200)) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @DeleteMapping("{id}")
    fun deleteProduct(@PathVariable id: String): Mono<Void> {
        return service.deleteProduct(id)
    }
}
