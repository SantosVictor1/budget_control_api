package com.budget.api.controller

import com.budget.api.message.request.PasswordRequest
import com.budget.api.message.request.UserRequest
import com.budget.api.model.User
import com.budget.api.message.response.error.ErrorResponse
import com.budget.api.message.response.success.SuccessResponse
import com.budget.api.message.response.success.UserResponse
import com.budget.api.service.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @ApiOperation(value = "Cadastra um usuário")
    @ApiResponses(
        ApiResponse(code = 201, message = "Cadastro realizado com sucesso", response = UserResponse::class),
        ApiResponse(code = 400, message = "Algum dado é inválido", response = ErrorResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @PostMapping("/users")
    fun createUser(@RequestBody @Valid  userRequest: UserRequest): ResponseEntity<Any> {
        var userResponse: UserResponse

        val userSaved =  userService.createUser(userRequest)
        userResponse = UserResponse(userSaved.id ,userSaved.name, userSaved.email, userSaved.cpf, userSaved.income)

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse)
    }

    @ApiOperation(value = "Atualiza a senha do usuário")
    @ApiResponses(
        ApiResponse(code = 200, message = "Senha alterada com sucesso!", response = SuccessResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 400, message = "Senha deve ter mais que 8 caracteres", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ErrorResponse::class)
    )
    @PatchMapping("/users/{id}")
    fun updatePassword(@RequestBody passwordRequest: PasswordRequest, @PathVariable id: Long): ResponseEntity<Any> {
        var user: User = userService.getById(id).get()
        var successResponse: SuccessResponse = SuccessResponse("Senha alterada com sucesso!", 200)

        user.password = passwordRequest.password
        userService.updateUser(user)

        return ResponseEntity.ok().body(successResponse)
    }

    @ApiOperation(value = "Retorna todos os usuários")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuários retornados com sucesso", response = UserResponse::class, responseContainer = "List"),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<MutableList<UserResponse>> {
        var userResponseList: MutableList<UserResponse> = mutableListOf<UserResponse>()
        val response = userService.getAll()

        response.forEach { user ->
            var userResponse = UserResponse(user.id, user.name, user.email, user.cpf, user.income)
            userResponseList.add(userResponse)
        }
        return ResponseEntity.ok().body(userResponseList)
    }

    @ApiOperation(value = "Retorna o usuário pelo Id")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuário retornado com sucesso", response = UserResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ErrorResponse::class)
    )
    @GetMapping("users/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val user = userService.getById(id).get()
        lateinit var userResponse: UserResponse

        user.let {
            userResponse = UserResponse(it.id, it.name, it.email, it.cpf, it.income)
        }

        return ResponseEntity.ok().body(userResponse)
    }

    @ApiOperation(value = "Deleta um usuário")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuário deletado com sucesso", response = SuccessResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ErrorResponse::class)
    )
    @DeleteMapping("/users/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        val successResponse = SuccessResponse("Usuário deletado com sucesso!", 204)

        userService.getById(id)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successResponse)
    }
}