package com.budget.api.service.impl

import com.budget.api.message.request.UserRequest
import com.budget.api.message.response.success.SuccessResponse
import com.budget.api.message.response.success.UserResponse
import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import com.budget.api.service.IUserService
import com.budget.api.service.exception.BudgetException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by Victor Santos on 25/11/2019
 */
@Service
class UserService : IUserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    override fun createUser(user: User): UserResponse {
        validatePostFields(user)

        val userSaved = userRepository.save(user)

        return UserResponse(userSaved.id, userSaved.name, userSaved.email, userSaved.cpf, userSaved.income, userSaved.spents)
    }

    override fun updateUser(user: User): SuccessResponse {
        userRepository.save(user)

        return SuccessResponse(200, "Senha alterada com sucesso")
    }

    override fun getAll(): List<User> {
        return userRepository.findAll()
    }

    override fun getById(id: Long): Optional<User> {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw BudgetException(404, mutableListOf("Usuário não encontrado"))
        }

        return user
    }

    override fun deleteById(id: Long) {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw BudgetException(404, mutableListOf("Usuário não encontrado"))
        }

        userRepository.deleteById(id)
    }

    override fun setUser(userRequest: UserRequest): User {
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