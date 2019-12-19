package com.budget.api.controller

import com.budget.api.message.request.SpentRequest
import com.budget.api.message.response.success.SpentResponse
import com.budget.api.message.response.success.SuccessResponse
import com.budget.api.model.Spent
import com.budget.api.model.User
import com.budget.api.service.SpentService
import com.budget.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
class SpentController {
    @Autowired
    private lateinit var spentService: SpentService

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/spents")
    fun newSpent(@RequestBody spentRequest: SpentRequest): ResponseEntity<SpentResponse> {
        var spentResponse: SpentResponse

        val spentSaved = spentService.saveSpent(spentRequest)
        spentResponse = SpentResponse(spentSaved.spentValue, spentSaved.spentDate, spentSaved.descritpion, spentSaved.user?.name)

        return ResponseEntity.ok().body(spentResponse)
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