package com.budget.api.controller

import com.budget.api.message.request.SpentRequest
import com.budget.api.message.response.success.SpentResponse
import com.budget.api.service.SpentService
import com.budget.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
class SpentController {
    @Autowired
    private lateinit var spentService: SpentService

    @PostMapping("/spents")
    fun newSpent(@RequestBody spentRequest: SpentRequest): ResponseEntity<SpentResponse> {
        return ResponseEntity.ok().body(spentService.saveSpent(spentRequest))
    }

    @GetMapping("/spents")
    fun getAll(): ResponseEntity<MutableList<SpentResponse>> {
        return ResponseEntity.ok().body(spentService.getSpents())
    }

    @GetMapping("/spents/user/{userId}")
    fun getAllFromUserId(@PathVariable userId: Long): ResponseEntity<MutableList<SpentResponse>> {
        return ResponseEntity.ok().body(spentService.getBydUserId(userId))
    }

    @GetMapping("spents/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<SpentResponse> {
        return ResponseEntity.ok().body(spentService.getById(id))
    }

    @DeleteMapping("spents/{id}")
    fun deleteSpentBydId(@PathVariable id: Long): ResponseEntity<Any> {
        spentService.deleteById(id)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}