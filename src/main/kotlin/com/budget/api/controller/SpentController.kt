package com.budget.api.controller

import com.budget.api.dto.request.SpentRequestDTO
import com.budget.api.dto.response.error.ObjectErrorResponse
import com.budget.api.dto.response.success.SpentResponseDTO
import com.budget.api.dto.response.success.SpentSumResponseDTO
import com.budget.api.service.ISpentService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

/**
 * Created by Victor Santos on 16/12/2019
 */
@RestController
@RequestMapping("/api/spending")
class SpentController(
    private val spentService: ISpentService
) {
    @PostMapping
    fun newSpent(@RequestBody @Valid spentRequestDTO: SpentRequestDTO): ResponseEntity<SpentResponseDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(spentService.saveSpent(spentRequestDTO))
    }

    @GetMapping("/user")
    fun getAllFromUserCpf(@RequestParam cpf: String): ResponseEntity<MutableList<SpentResponseDTO>> {
        return ResponseEntity.ok().body(spentService.getByUserCpf(cpf))
    }

    @GetMapping("/sum")
    fun getFinalSumByUserCpf(@RequestParam cpf: String): ResponseEntity<SpentSumResponseDTO> {
        return ResponseEntity.ok(spentService.getSpentSumByUserCpf(cpf))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<SpentResponseDTO> {
        return ResponseEntity.ok().body(spentService.getById(id))
    }

    @DeleteMapping("/{id}")
    fun deleteSpentBydId(@PathVariable id: String): ResponseEntity<Any> {
        spentService.deleteById(id)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}