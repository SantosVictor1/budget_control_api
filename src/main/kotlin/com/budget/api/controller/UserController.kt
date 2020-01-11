package com.budget.api.controller

import com.budget.api.exception.BudgetException
import com.budget.api.dto.request.PasswordRequestDTO
import com.budget.api.dto.request.UserRequestDTO
import com.budget.api.dto.response.error.ErrorResponse
import com.budget.api.dto.response.success.SuccessResponseDTO
import com.budget.api.dto.response.success.UserResponseDTO
import com.budget.api.model.User
import com.budget.api.service.IUserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Created by Victor Santos on 24/11/2019
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
class UserController(
    private val userService: IUserService
) {
    @ApiOperation(value = "Cadastra um usuário")
    @ApiResponses(
        ApiResponse(code = 201, message = "Cadastro realizado com sucesso", response = UserResponseDTO::class),
        ApiResponse(code = 400, message = "Algum dado é inválido", response = ErrorResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @PostMapping("/users")
    fun createUser(
        @RequestBody @Valid userRequestDTO: UserRequestDTO,
        result: BindingResult
    ): ResponseEntity<Any> {
        validateResult(result)

        val user = userService.setUser(userRequestDTO)

        return ResponseEntity(userService.createUser(user), HttpStatus.CREATED)
    }

    @ApiOperation(value = "Atualiza a senha do usuário")
    @ApiResponses(
        ApiResponse(code = 200, message = "Senha alterada com sucesso!", response = SuccessResponseDTO::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 400, message = "Senha deve ter mais que 8 caracteres", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ErrorResponse::class)
    )
    @PatchMapping("/users/{id}")
    fun updatePassword(
        @RequestBody @Valid passwordRequestDTO: PasswordRequestDTO,
        result: BindingResult,
        @PathVariable id: Long
    ): ResponseEntity<Any> {
        validateResult(result)

        var user: User = userService.getById(id).get()

        user.password = passwordRequestDTO.password

        return ResponseEntity.ok().body(userService.updateUser(user))
    }

    @ApiOperation(value = "Retorna todos os usuários")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuários retornados com sucesso", response = UserResponseDTO::class, responseContainer = "List"),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<MutableList<UserResponseDTO>> {
        var userResponseDTOList: MutableList<UserResponseDTO> = mutableListOf<UserResponseDTO>()
        val response = userService.getAll()

        response.forEach { user ->
            var userResponse = UserResponseDTO(user.id!!, user.name, user.email, user.cpf, user.income)
            userResponseDTOList.add(userResponse)
        }
        return ResponseEntity.ok().body(userResponseDTOList)
    }

    @ApiOperation(value = "Retorna o usuário pelo Id")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuário retornado com sucesso", response = UserResponseDTO::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ErrorResponse::class)
    )
    @GetMapping("users/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val user = userService.getById(id).get()
        lateinit var userResponseDTO: UserResponseDTO

        user.let {
            userResponseDTO = UserResponseDTO(it.id!!, it.name, it.email, it.cpf, it.income)
        }

        return ResponseEntity.ok().body(userResponseDTO)
    }

    @ApiOperation(value = "Deleta um usuário")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuário deletado com sucesso", response = SuccessResponseDTO::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ErrorResponse::class)
    )
    @DeleteMapping("/users/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        userService.deleteById(id)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    private fun validateResult(bindingResult: BindingResult) {
        if (bindingResult.hasErrors()) {
            var errorList = mutableListOf<String>()
            bindingResult.allErrors.forEach {
                errorList.add(it.defaultMessage.toString())
            }

            throw BudgetException(400, errorList)
        }
    }
}