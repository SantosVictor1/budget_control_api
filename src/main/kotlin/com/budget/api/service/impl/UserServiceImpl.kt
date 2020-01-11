package com.budget.api.service.impl

import com.budget.api.common.BudgetErrorCode
import com.budget.api.dto.request.UserRequestDTO
import com.budget.api.dto.response.success.SuccessResponseDTO
import com.budget.api.dto.response.success.UserResponseDTO
import com.budget.api.exception.DuplicatedResourceException
import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import com.budget.api.service.IUserService
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by Victor Santos on 25/11/2019
 */
@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : IUserService {
    override fun createUser(userRequestDTO: UserRequestDTO): UserResponseDTO {
        validatePostFields(userRequestDTO)
        val user = User.toEntity(userRequestDTO)

        return UserResponseDTO.toDto(userRepository.save(user))
    }

    override fun updateUser(user: User): SuccessResponseDTO {
        userRepository.save(user)

        return SuccessResponseDTO(200)
    }

    override fun getAll(): List<User> {
        return userRepository.findAll()
    }

    override fun getById(id: Long): Optional<User> {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
//            throw BudgetException(404, mutableListOf("Usuário não encontrado"))
        }

        return user
    }

    override fun deleteById(id: Long) {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
//            throw BudgetException(404, mutableListOf("Usuário não encontrado"))
        }

        userRepository.deleteById(id)
    }

    override fun setUser(userRequestDTO: UserRequestDTO): User {
        return User(null, userRequestDTO.name, userRequestDTO.cpf, userRequestDTO.email, userRequestDTO.password, userRequestDTO.income, null)
    }

    /**
     * Método responsável por validar os campos no POST
     *
     *  @param  user  usuário a ser validado
     */
    private fun validatePostFields(userRequestDTO: UserRequestDTO) {
        if (existsByCpf(userRequestDTO.cpf)) {
            duplicatedResourceException(BudgetErrorCode.BUDGET011.code, "cpf", "userRequestDTO")
        }

        if (existsByEmail(userRequestDTO.email)) {
            duplicatedResourceException(BudgetErrorCode.BUDGET012.code, "email", "userRequestDTO")
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

    private fun duplicatedResourceException(errorCode: String, field: String, objectName: String) {
        throw DuplicatedResourceException(errorCode, field, objectName)
    }
}