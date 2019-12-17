package com.budget.api.controller

import com.budget.api.message.request.SpentRequest
import com.budget.api.model.Spent
import com.budget.api.model.User
import com.budget.api.service.SpentService
import com.budget.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
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
    fun newSpent(@RequestBody spentRequest: SpentRequest): ResponseEntity<Any> {
        var spent: Spent = Spent()
        val user: User = userService.getById(spentRequest.userId).get()

//        spent.spentDate = Date()
        spent.spentPlace = spentRequest.spentPlace
        spent.spentValue = spentRequest.spentValue
        spent.user = user

        return ResponseEntity.ok().body(spentService.saveSpent(spent))
    }

    @GetMapping("/spents")
    fun getAll(): ResponseEntity<List<Spent>> {
        return ResponseEntity.ok().body(spentService.getSpents())
    }

    @GetMapping("/spents/{userId}")
    fun getAll(@PathVariable userId: Long): ResponseEntity<List<Spent>> {
        return ResponseEntity.ok().body(spentService.getBydUserId(userId))
    }
}