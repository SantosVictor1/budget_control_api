package com.budget.api.controller

import com.budget.api.dto.request.SpentRequestDTO
import com.budget.api.dto.response.error.ObjectErrorResponse
import com.budget.api.dto.response.success.SpentResponseDTO
import com.budget.api.service.ISpentService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Created by Victor Santos on 16/12/2019
 */
@RestController
@RequestMapping("/api/spents")
@CrossOrigin("*")
class SpentController(
    private val spentService: ISpentService
) {
    @ApiOperation(value = "Salva gasto de um usuário")
    @ApiResponses(
        ApiResponse(code = 201, message = "Cadastro realizado com sucesso", response = SpentResponseDTO::class),
        ApiResponse(code = 400, message = "Algum dado é inválido", response = ObjectErrorResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ObjectErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @PostMapping
    fun newSpent(@RequestBody @Valid spentRequestDTO: SpentRequestDTO): ResponseEntity<SpentResponseDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(spentService.saveSpent(spentRequestDTO))
    }

    @ApiOperation(value = "Retorna gastos de um usuário")
    @ApiResponses(
        ApiResponse(code = 200, message = "Gastos retornados com sucesso", response = SpentResponseDTO::class, responseContainer = "List"),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ObjectErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ObjectErrorResponse::class)
    )
    @GetMapping("/user")
    fun getAllFromUserCpf(@RequestParam cpf: String): ResponseEntity<MutableList<SpentResponseDTO>> {
        return ResponseEntity.ok().body(spentService.getByUserCpf(cpf))
    }

    @ApiOperation(value = "Retorna um gasto pelo id")
    @ApiResponses(
        ApiResponse(code = 200, message = "Gasto retornado com sucesso", response = SpentResponseDTO::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ObjectErrorResponse::class),
        ApiResponse(code = 404, message = "Gasto não encontrado", response = ObjectErrorResponse::class)
    )
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<SpentResponseDTO> {
        return ResponseEntity.ok().body(spentService.getById(id))
    }

    @ApiOperation(value = "Deleta um gasto pelo id")
    @ApiResponses(
        ApiResponse(code = 204, message = "Gasto deletado com sucesso"),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ObjectErrorResponse::class),
        ApiResponse(code = 404, message = "Gasto não encontrado", response = ObjectErrorResponse::class)
    )
    @DeleteMapping("/{id}")
    fun deleteSpentBydId(@PathVariable id: Long): ResponseEntity<Any> {
        spentService.deleteById(id)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}