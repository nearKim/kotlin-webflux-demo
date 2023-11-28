package com.example.productservice.entity

import org.springframework.data.annotation.Id

class Product {
    @Id
    var id: String? = null
    var description: String? = null
    var price = 0
}