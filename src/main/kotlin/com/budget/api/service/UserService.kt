package com.budget.api.service

import com.budget.api.message.request.UserRequest
import com.budget.api.message.response.error.ErrorResponse
import com.budget.api.message.response.success.UserResponse
import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import com.budget.api.service.exception.UserException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun existsByCpf(cpf: String): Boolean {
        return userRepository.existsByCpf(cpf)
    }

    fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun createUser(userRequest: UserRequest): User {
        val user = setUser(userRequest)
        validatePostFields(user)

        return userRepository.save(user)
    }

    fun updateUser(user: User): User {
        validatePassword(user)

        return userRepository.save(user)
    }

    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    fun getById(id: Long): Optional<User> {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw UserException(404, "Usuário não encontrado")
        }

        return user
    }

    fun deleteById(id: Long) {
        return userRepository.deleteById(id)
    }

    /*
    *  Método responsável por atribuir as váriaveis do DTO ao model
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
        user.income = userRequest.income

        return user
    }

    fun validatePassword(user: User) {
        if (user.password?.length!! < 8) {
            throw UserException(400, "Senha deve ser maior que 8 caracteres")
        }
    }

    fun validatePostFields(user: User) {
        var pattern: Pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")
        var matcher: Matcher = pattern.matcher(user.email)

        if (user.name?.isEmpty()!!) {
            throw UserException(400, "Nome obrigatório")
        }

        if (user.name?.length!! < 3 || user.name?.length!! > 80) {
            throw UserException(400, "Nome deve ter tamanho entre 3 e 80 caracteres")
        }

        if (user.cpf?.isEmpty()!! || user.cpf?.length != 11) {
            throw UserException(400, "CPF inválido")
        }

        if (user.email?.isEmpty() !!) {
            throw UserException(400, "Email obrigatório")
        }

        if (user.income == null) {
            throw UserException(400, "Renda inválida")
        }

        if (!matcher.matches()) {
            throw UserException(400, "Email inválido")
        }

        if (userRepository.existsByCpf(user.cpf!!)) {
            throw UserException(400, "CPF já cadastrado")
        }

        if (userRepository.existsByEmail(user.email!!)) {
            throw UserException(400, "Email já cadastrado")
        }

        validatePassword(user)
    }
}