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
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    private fun validateFields(user: User, bindingResult: BindingResult) {
        if (user.password?.length!! < 8) {
            bindingResult.addError((ObjectError("Senha", "Senha deve ser maior que 8 caracteres")))
            return
        }
    }

    private fun validatePostFields(user: User, bindingResult: BindingResult) {
        if (user.name?.isEmpty()!!) {
            bindingResult.addError(ObjectError("Nome", "Nome obrigatório"))
            return
        }

        if (user.cpf?.isEmpty()!! || user.cpf?.length != 11) {
            bindingResult.addError(ObjectError("CPF", "CPF inválido"))
            return
        }

        if (user.email?.isEmpty() !!) {
            bindingResult.addError(ObjectError("Email", "Email obrigatório"))
            return
        }

        if (userService.existsByCpf(user.cpf)) {
            bindingResult.addError((ObjectError("Cpf", "CPF já cadastrado")))
            return
        }

        if (userService.existsByEmail(user.email)) {
            bindingResult.addError((ObjectError("Email", "Email já cadastrado")))
            return
        }

        validateFields(user, bindingResult)
    }

    /*
    *  Método responsável por salvar as variáveis da requisição POST no model
    *
    * @params UserRequest
    *
    * @return User
    */
    private fun setUser(userRequest: UserRequest): User {
        var user: User = User()
        user.cpf = userRequest.cpf
        user.name = userRequest.name
        user.password = userRequest.password
        user.email = userRequest.email

        return user
    }

    private fun setErrorMessage(erroMessage: String, status: Int): ErrorResponse {
        return ErrorResponse(status, erroMessage)
    }

    @ApiOperation(value = "Cadastra um usuário")
    @ApiResponses(
        ApiResponse(code = 201, message = "Cadastro realizado com sucesso", response = UserResponse::class),
        ApiResponse(code = 400, message = "Algum dado não está válido", response = ErrorResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @PostMapping("/users")
    fun createUser(@RequestBody userRequest: UserRequest, bindingResult: BindingResult): ResponseEntity<Any> {
        var userResponse: UserResponse
        var errorResponse: ErrorResponse
        val user: User = setUser(userRequest)

        validatePostFields(user, bindingResult)

        if (bindingResult.hasErrors()) {
            val error = bindingResult.allErrors[0].defaultMessage

            return ResponseEntity.badRequest().body(setErrorMessage(error!!, 400))
        }

        val userSaved =  userService.save(user)
        userResponse = UserResponse(userSaved.id ,userSaved.name, userSaved.email, userSaved.cpf)

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse)
    }

    @ApiOperation(value = "Atualiza senha do usuário")
    @ApiResponses(
        ApiResponse(code = 200, message = "Senha alterada com sucesso!", response = SuccessResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 400, message = "Senha deve ser maior que 8 caracteres", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Servidor não encontrado")
    )
    @PutMapping("/users/{id}")
    fun updateUser(@RequestBody passwordRequest: PasswordRequest,
                   bindingResult: BindingResult,
                   @PathVariable id: Long): ResponseEntity<Any> {
        var successResponse: SuccessResponse = SuccessResponse("Senha alterada com sucesso!", 200)
        lateinit var errorResponse: ErrorResponse
        var user: User = userService.getById(id).get()

        user.password = passwordRequest.password
        validateFields(user, bindingResult)

        if (bindingResult.hasErrors()) {
            val error = bindingResult.allErrors[0].defaultMessage

            return ResponseEntity.badRequest().body(setErrorMessage(error!!, 400))
        }

        userService.save(user)

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
            var userResponse = UserResponse(user.id, user.name, user.email, user.cpf)
            userResponseList.add(userResponse)
        }
        return ResponseEntity.ok(userResponseList)
    }

    @ApiOperation(value = "Retorna o usuário pelo Id")
    @ApiResponses(
        ApiResponse(code = 200, message = "Usuário retornado com sucesso", response = UserResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ErrorResponse::class)
    )
    @GetMapping("users/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val user: Optional<User> = userService.getById(id)
        lateinit var userResponse: UserResponse
        lateinit var errorResponse: ErrorResponse

        if (!user.isPresent) {
            errorResponse = ErrorResponse(404, "Usuário não encontrado")
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
        }

        user.let {
            userResponse = UserResponse(it.get().id, it.get().name, it.get().email, it.get().cpf)
        }

        return ResponseEntity.ok().body(userResponse)
    }

    @ApiOperation(value = "Deleta um usuário")
    @ApiResponses(
        ApiResponse(code = 204, message = "Usuário deletado com sucesso", response = SuccessResponse::class),
        ApiResponse(code = 401, message = "Você não está autenticado", response = ErrorResponse::class),
        ApiResponse(code = 404, message = "Usuário não encontrado", response = ErrorResponse::class)
    )
    @DeleteMapping("/users/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        val user: Optional<User> = userService.getById(id)
        var errorResponse: ErrorResponse = ErrorResponse(404, "Usuário não encontrado")
        val successResponse: SuccessResponse = SuccessResponse("Usuário deletado com sucesso!", 204)

        if (user.isPresent) {
            userService.deleteById(id)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successResponse)
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }
}