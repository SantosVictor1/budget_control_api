package com.budget.api.controller

import com.budget.api.model.User
import com.budget.api.response.Response
import com.budget.api.response.error.ErrorResponse
import com.budget.api.response.success.UserResponse
import com.budget.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    fun validateFields(user: User, bindingResult: BindingResult) {
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

        if (user.password?.length!! < 8) {
            bindingResult.addError((ObjectError("Senha", "Senha deve ser maior que 8 caracteres")))
            return
        }
    }

    @PostMapping("/users")
    fun createUser(@RequestBody user: User, bindingResult: BindingResult): ResponseEntity<Response> {
        var userResponse: UserResponse
        var errorResponse: ErrorResponse

        validateFields(user, bindingResult)

        if (bindingResult.hasErrors()) {
            val error = bindingResult.allErrors[0].defaultMessage
            errorResponse = ErrorResponse(400, error!!)

            return ResponseEntity.badRequest().body(errorResponse)
        }

        val userSaved =  userService.save(user)
        userResponse = UserResponse(userSaved.id ,userSaved.name, userSaved.email, userSaved.cpf)

        return ResponseEntity.ok(userResponse)
    }

    @GetMapping("/users")
    @ResponseBody
    fun request(): ResponseEntity<MutableList<UserResponse>> {
        var userResponseList: MutableList<UserResponse> = mutableListOf<UserResponse>()
        val response = userService.getAll()

        response.forEach { user ->
            var user = UserResponse(user.id, user.name, user.email, user.cpf)
            userResponseList.add(user)
        }
        return ResponseEntity.ok(userResponseList)
    }

    @GetMapping("users/{id}")
    @ResponseBody
    fun getById(@PathVariable id: Long): ResponseEntity<UserResponse> {
        val user: User = userService.getById(id)
        var userResponse: UserResponse = UserResponse(user.id, user.name, user.email, user.cpf)

        return ResponseEntity.ok(userResponse)
    }

    @DeleteMapping("/users/{id}")
    @ResponseBody
    fun deleteById(@PathVariable id: Long): ResponseEntity<String> {
        userService.deleteById(id)

        return ResponseEntity.ok("Usuário deletado com sucesso")
    }
}