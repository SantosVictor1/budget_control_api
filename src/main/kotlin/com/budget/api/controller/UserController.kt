package com.budget.api.controller

import com.budget.api.dto.request.PasswordRequestDTO
import com.budget.api.dto.request.UserRequestDTO
import com.budget.api.dto.response.error.ObjectErrorResponse
import com.budget.api.dto.response.success.SuccessResponseDTO
import com.budget.api.dto.response.success.UserResponseDTO
import com.budget.api.model.User
import com.budget.api.service.IUserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Created by Victor Santos on 24/11/2019
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
class UserController(
    private val userService: IUserService
) {
    @ApiOperation(value = "Cadastra um usuário")
    @ApiResponses(
        ApiResponse(code = 201, message = "Cadastro realizado com sucesso", response = UserResponseDTO::class),
        ApiResponse(code = 400, message = "Algum dado é inválido", response = ObjectErrorResponse::class),
        ApiResponse(code = 422, message = "Algum dado está duplicado", response = ObjectErrorResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ObjectErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @PostMapping
    fun createUser(@RequestBody @Valid userRequestDTO: UserRequestDTO): ResponseEntity<Any> {
        return ResponseEntity(userService.createUser(userRequestDTO), HttpStatus.CREATED)
    }

    @ApiOperation(value = "Atualiza a senha do usuário")
    @ApiResponses(
        ApiResponse(code = 200, message = "Senha alterada com sucesso!", response = SuccessResponseDTO::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ObjectErrorResponse::class),
        ApiResponse(code = 400, message = "Senha deve ter mais que 8 caracteres", response = ObjectErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ObjectErrorResponse::class)
    )
    @PatchMapping("/password")
    fun updatePassword(@RequestBody @Valid passwordRequestDTO: PasswordRequestDTO): ResponseEntity<Any> {
        return ResponseEntity.ok().body(userService.updateUser(passwordRequestDTO))
    }

    @ApiOperation(value = "Retorna todos os usuários")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuários retornados com sucesso", response = UserResponseDTO::class, responseContainer = "List"),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ObjectErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @GetMapping
    fun getAllUsers(): ResponseEntity<MutableList<UserResponseDTO>> {
        return ResponseEntity.ok().body(userService.getAll())
    }

    @ApiOperation(value = "Retorna o usuário pelo Id")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuário retornado com sucesso", response = UserResponseDTO::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ObjectErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ObjectErrorResponse::class)
    )
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.ok().body(userService.getById(id))
    }

    @ApiOperation(value = "Retorna o usuário pelo Cpf")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuário retornado com sucesso", response = UserResponseDTO::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ObjectErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ObjectErrorResponse::class)
    )
    @GetMapping("/by-cpf")
    fun getByCpf(@RequestParam(required = true, defaultValue = "") @ApiParam(required = true) cpf: String): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.ok().body(userService.getByCpf(cpf))
    }

    @ApiOperation(value = "Deleta um usuário")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuário deletado com sucesso", response = SuccessResponseDTO::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ObjectErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ObjectErrorResponse::class)
    )
    @DeleteMapping("/delete")
    fun deleteUser(@RequestParam(required = true, defaultValue = "") @ApiParam(required = true) cpf: String): ResponseEntity<Any> {
        userService.deleteByCpf(cpf)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}