package com.budget.budget.controllers

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
class ApiController {

    @GetMapping("/data")
    fun request(): String {
        return "Hello World";
    }
}