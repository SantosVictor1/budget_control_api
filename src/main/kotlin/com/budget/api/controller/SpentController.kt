package com.budget.api.controller

import org.springframework.web.bind.annotation.*

/**
 * Created by Victor Santos on 16/12/2019
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
class SpentController(
//    private val spentService: ISpentService
) {
//    @ApiOperation(value = "Salva gasto de um usuário")
//    @ApiResponses(
//        ApiResponse(code = 201, message = "Cadastro realizado com sucesso", response = SpentResponseDTO::class),
//        ApiResponse(code = 400, message = "Algum dado é inválido", response = ErrorResponse::class),
//        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
//        ApiResponse(code = 404, message = "Servidor não encontrado")
//    )
//    @PostMapping("/spents")
//    fun newSpent(
//        @RequestBody @Valid spentRequestDTO: SpentRequestDTO,
//        result: BindingResult
//    ): ResponseEntity<Any> {
//        if (result.hasErrors()) {
//            val errorsList = mutableListOf<ErrorSupport>()
//            result.allErrors.forEach {
//                errorsList.add(ErrorSupport(it.defaultMessage.toString()))
//            }
//
//            return ResponseEntity(ErrorResponse(400, errorsList), HttpStatus.BAD_REQUEST)
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(spentService.saveSpent(spentRequestDTO))
//    }
//
//    @ApiOperation(value = "Retorna todos os gastos cadastrados")
//    @ApiResponses(
//        ApiResponse(code = 200, message = "Gastos retornados com sucesso", response = SpentResponseDTO::class, responseContainer = "List"),
//        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
//        ApiResponse(code = 404, message = "Servidor não encontrado")
//    )
//    @GetMapping("/spents")
//    fun getAll(): ResponseEntity<MutableList<SpentResponseDTO>> {
//        return ResponseEntity.ok().body(spentService.getSpents())
//    }
//
//    @ApiOperation(value = "Retorna gastos de um usuário")
//    @ApiResponses(
//        ApiResponse(code = 200, message = "Gastos retornados com sucesso", response = SpentResponseDTO::class, responseContainer = "List"),
//        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
//        ApiResponse(code = 404, message = "Usuário não encontrado", response = ErrorResponse::class)
//    )
//    @GetMapping("/spents/user/{userId}")
//    fun getAllFromUserId(@PathVariable userId: Long): ResponseEntity<MutableList<SpentResponseDTO>> {
//        return ResponseEntity.ok().body(spentService.getBydUserId(userId))
//    }
//
//    @ApiOperation(value = "Retorna um gasto pelo id")
//    @ApiResponses(
//        ApiResponse(code = 200, message = "Gasto retornado com sucesso", response = SpentResponseDTO::class),
//        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
//        ApiResponse(code = 404, message = "Gasto não encontrado", response = ErrorResponse::class)
//    )
//    @GetMapping("spents/{id}")
//    fun getById(@PathVariable id: Long): ResponseEntity<SpentResponseDTO> {
//        return ResponseEntity.ok().body(spentService.getById(id))
//    }
//
//    @ApiOperation(value = "Deleta um gasto pelo id")
//    @ApiResponses(
//        ApiResponse(code = 204, message = "Gasto deletado com sucesso"),
//        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
//        ApiResponse(code = 404, message = "Gasto não encontrado", response = ErrorResponse::class)
//    )
//    @DeleteMapping("spents/{id}")
//    fun deleteSpentBydId(@PathVariable id: Long): ResponseEntity<Any> {
//        spentService.deleteById(id)
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
//    }
}