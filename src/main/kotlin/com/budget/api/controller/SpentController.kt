package com.budget.api.controller

import com.budget.api.message.request.SpentRequest
import com.budget.api.message.response.error.ErrorResponse
import com.budget.api.message.response.error.ErrorSupport
import com.budget.api.message.response.success.SpentResponse
import com.budget.api.service.SpentService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Created by Victor Santos on 16/12/2019
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
class SpentController {
    @Autowired
    private lateinit var spentService: SpentService

    @ApiOperation(value = "Salva gasto de um usuário")
    @ApiResponses(
        ApiResponse(code = 201, message = "Cadastro realizado com sucesso", response = SpentResponse::class),
        ApiResponse(code = 400, message = "Algum dado é inválido", response = ErrorResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @PostMapping("/spents")
    fun newSpent(
        @RequestBody @Valid spentRequest: SpentRequest,
        result: BindingResult
    ): ResponseEntity<Any> {
        if (result.hasErrors()) {
            val errorsList = mutableListOf<ErrorSupport>()
            result.allErrors.forEach {
                errorsList.add(ErrorSupport(it.defaultMessage.toString()))
            }

            return ResponseEntity(ErrorResponse(400, errorsList), HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(spentService.saveSpent(spentRequest))
    }

    @ApiOperation(value = "Retorna todos os gastos cadastrados")
    @ApiResponses(
        ApiResponse(code = 200, message = "Gastos retornados com sucesso", response = SpentResponse::class, responseContainer = "List"),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @GetMapping("/spents")
    fun getAll(): ResponseEntity<MutableList<SpentResponse>> {
        return ResponseEntity.ok().body(spentService.getSpents())
    }

    @ApiOperation(value = "Retorna gastos de um usuário")
    @ApiResponses(
        ApiResponse(code = 200, message = "Gastos retornados com sucesso", response = SpentResponse::class, responseContainer = "List"),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ErrorResponse::class)
    )
    @GetMapping("/spents/user/{userId}")
    fun getAllFromUserId(@PathVariable userId: Long): ResponseEntity<MutableList<SpentResponse>> {
        return ResponseEntity.ok().body(spentService.getBydUserId(userId))
    }

    @ApiOperation(value = "Retorna um gasto pelo id")
    @ApiResponses(
        ApiResponse(code = 200, message = "Gasto retornado com sucesso", response = SpentResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Gasto não encontrado", response = ErrorResponse::class)
    )
    @GetMapping("spents/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<SpentResponse> {
        return ResponseEntity.ok().body(spentService.getById(id))
    }

    @ApiOperation(value = "Deleta um gasto pelo id")
    @ApiResponses(
        ApiResponse(code = 204, message = "Gasto deletado com sucesso"),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Gasto não encontrado", response = ErrorResponse::class)
    )
    @DeleteMapping("spents/{id}")
    fun deleteSpentBydId(@PathVariable id: Long): ResponseEntity<Any> {
        spentService.deleteById(id)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}