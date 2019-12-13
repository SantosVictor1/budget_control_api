package com.budget.api.service

import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import com.budget.api.service.exception.UserException
import org.springframework.beans.factory.annotation.Autowired
import java.util.regex.Matcher
import java.util.regex.Pattern

object UserValidation {
    @Autowired
    lateinit var userRepository: UserRepository

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

        if (!matcher.matches()) {
            throw UserException(400, "Email inválido")
        }

        if (userRepository.existsByCpf(user.cpf)) {
            throw UserException(400, "CPF já cadastrado")
        }

        if (userRepository.existsByEmail(user.email)) {
            throw UserException(400, "Email já cadastrado")
        }

        validatePassword(user)
    }
}