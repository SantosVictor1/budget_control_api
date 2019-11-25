package com.budget.api.controller

import com.budget.api.model.User
import com.budget.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
class ApiController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/users")
    fun createUser(@RequestBody user: User): ResponseEntity<String> {
        var currentUser: User = User()

        try {
            currentUser.email = user.email
            currentUser.name = user.name
            currentUser.cpf = user.cpf
            currentUser.password = user.password

            if (userService.existsByIdAndEmailOrCpf(currentUser.email, currentUser.cpf)) {
                throw Exception("Email ou cpf ja cadastrados!", Throwable())
            }

            userService.save(currentUser)
        } catch (e: Exception) {
            if (e.toString().contains("tamanho deve estar", ignoreCase = true)) {
                return ResponseEntity.badRequest().body("Senha deve ter no mínimo 8 caracteres")
            }

            return ResponseEntity.badRequest().body(e.toString())
        }

        return ResponseEntity.ok("Criado com sucesso")
    }

//    @GetMapping("/users")
//    @ResponseBody
//    fun request(): MutableList<User>? {
//        return userRepository.findAll()
//    }
}