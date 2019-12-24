package com.budget.api.service

import com.budget.api.message.request.UserRequest
import com.budget.api.message.response.error.ErrorSupport
import com.budget.api.message.response.success.SuccessResponse
import com.budget.api.message.response.success.UserResponse
import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import com.budget.api.service.exception.BudgetException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Victor Santos on 25/11/2019
 */
@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    /**
     * Método responsável por cadastrar o usuário
     *
     * @param  userRequest  DTO de User
     * @return O usuário criado
     */
    fun createUser(userRequest: UserRequest): UserResponse {
        val user = setUser(userRequest)
        validatePostFields(user)

        val userSaved = userRepository.save(user)

        return UserResponse(userSaved.id, userSaved.name, userSaved.email, userSaved.cpf, userSaved.income, userSaved.spents)
    }

    /**
     * Método responsável por atualizar a senha de algum usuário
     *
     * @param  user  com a nova senha
     * @return DTO de sucesso
     */
    fun updateUser(user: User): SuccessResponse {
        userRepository.save(user)

        return SuccessResponse(200, "Senha alterada com sucesso")
    }

    /**
     * Método responsável por listar todos os usuários cadastrados
     *
     * @return Lista dos usuários cadastrados
     */
    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    /**
     * Método responsável por listar o usuário pelo Id
     *
     * @param  id  do usuário a ser encontrado
     * @return Optional do tipo User
     */
    fun getById(id: Long): Optional<User> {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw BudgetException(404, mutableListOf("Usuário não encontrado"))
        }

        return user
    }

    /**
     * Método responsável por usuário pelo Id
     *
     * @param  id  do usuário a ser deletado
     */
    fun deleteById(id: Long) {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw BudgetException(404, mutableListOf("Usuário não encontrado"))
        }

        userRepository.deleteById(id)
    }

    /**
     * Método responsável por criar um objeto User
     *
     * @param userRequest DTO que será usado para criar um User
     *
     * @return User criado
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

    /**
     * Método responsável por validar os campos no POST
     *
     *  @param  user  usuário a ser validado
     */
    private fun validatePostFields(user: User) {
        if (existsByCpf(user.cpf!!)) {
            throw BudgetException(400, mutableListOf("CPF já cadastrado"))
        }

        if (existsByEmail(user.email!!)) {
            throw BudgetException(400, mutableListOf("Email já cadastrado"))
        }
    }

    /**
     * Método responsável por verificar se o cpf já existe
     *
     * @param  cpf  CPF para verificar duplicidade
     * @return true se já existir o CPF
     */
    private fun existsByCpf(cpf: String): Boolean {
        return userRepository.existsByCpf(cpf)
    }

    /**
     * Método responsável por verificar se o email já existe
     *
     * @param  email  Email para verificar duplicidade
     * @return true se o email já existir
     */
    private fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
}