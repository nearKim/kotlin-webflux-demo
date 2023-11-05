package com.example.webfluxdemo.controller

import com.example.webfluxdemo.dto.Response
import com.example.webfluxdemo.service.MathService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/math")
class MathController(val mathService: MathService) {


    @GetMapping("/square/{input}")
    fun findSquare(@PathVariable input: Int):Response = mathService.findSquare(input)

    @GetMapping("/table/{input}")
    fun multiplcationTable(@PathVariable input: Int):List<Response> = mathService.multiplicationTable(input)

}