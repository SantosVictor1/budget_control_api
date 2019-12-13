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

    fun createUser(userRequest: UserRequest): User {
        val user = setUser(userRequest)
        UserValidation.validatePostFields(user)

        return userRepository.save(user)
    }

    fun updateUser(user: User): User {
        UserValidation.validatePassword(user)

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

        return user
    }
}