package com.budget.api.controller

import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
class ApiController {

    @Autowired
    private var userRepository: UserRepository? = null;

    @PostMapping("/users")
    fun createUser(@RequestBody user: User): String {
        var currentUser: User = User();

        currentUser.email = user.email;
        currentUser.name = user.name;
        currentUser.cpf = user.cpf;
        currentUser.password = user.password;
        userRepository?.save(currentUser);

        return "Criado com sucesso";
    }

    @GetMapping("/users")
    @ResponseBody
    fun request(): MutableList<User>? {
        return userRepository?.findAll();
    }
}