package com.budget.api.service.impl

import com.budget.api.common.BudgetErrorCode
import com.budget.api.dto.request.PasswordRequestDTO
import com.budget.api.dto.request.UserRequestDTO
import com.budget.api.dto.response.success.SuccessResponseDTO
import com.budget.api.dto.response.success.UserResponseDTO
import com.budget.api.exception.DuplicatedResourceException
import com.budget.api.exception.ResourceNotFoundException
import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import com.budget.api.service.IUserService
import org.springframework.stereotype.Service

/**
 * Created by Victor Santos on 25/11/2019
 */
@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : IUserService {
    override fun createUser(userRequestDTO: UserRequestDTO): UserResponseDTO {
        validatePostFields(userRequestDTO)
        val user = User.fromUserRequestToEntity(userRequestDTO)

        return UserResponseDTO.toDto(userRepository.save(user))
    }

    override fun updateUser(passwordRequestDTO: PasswordRequestDTO): SuccessResponseDTO {
        var user: User = User.fromUserResponseToEntity(getByCpf(passwordRequestDTO.cpf))

        user.password = passwordRequestDTO.password

        userRepository.save(user)

        return SuccessResponseDTO(200)
    }

    override fun getAll(): MutableList<UserResponseDTO> {
        var userResponseDTOList: MutableList<UserResponseDTO> = mutableListOf()
        val users = userRepository.findAll()

        users.forEach {
            userResponseDTOList.add(UserResponseDTO.toDto(it))
        }

        return userResponseDTOList
    }

    override fun getById(id: Long): UserResponseDTO {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            resourceNotFoundException(BudgetErrorCode.BUDGET013.code, "id", User.javaClass.canonicalName)
        }

        return UserResponseDTO.toDto(user.get())
    }

    override fun getByCpf(cpf: String): UserResponseDTO {
        val user = userRepository.findByCpf(cpf)

        if (user == null) {
            resourceNotFoundException(BudgetErrorCode.BUDGET013.code, "cpf", User.javaClass.canonicalName)
        }

        return UserResponseDTO.toDto(user!!)
    }

    override fun deleteByCpf(cpf: String) {
        val user = User.fromUserResponseToEntity(getByCpf(cpf))

        userRepository.deleteById(user.id!!)
    }

    /**
     * Method that verifies if the cpf or email already exists in the system.
     * If exists one of them, is called the method that throws an exception
     *
     *  @param  userRequestDTO  DTO used for the verification
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
     * Method that calls the repository's method with same name
     * to validate the cpf
     *
     * @param  cpf  CPF to check duplicity
     * @return true if exists, false otherwise
     */
    private fun existsByCpf(cpf: String): Boolean {
        return userRepository.existsByCpf(cpf)
    }

    /**
     * Method that calls the repository's method with same name
     * to validate the email
     *
     * @param  email  Email to check duplicity
     * @return true if exists, false otherwise
     */
    private fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    /**
     * Method that throws the DuplicatedResourceException when exists a
     * user with cpf or email already registered in the system
     *
     * @param  errorCode  code from messages.properties
     * @param  field  the field used for validation
     * @param  objectName  then name of the object used for validation
     */
    private fun duplicatedResourceException(errorCode: String, field: String, objectName: String) {
        throw DuplicatedResourceException(errorCode, field, objectName)
    }

    /**
     * Method that throws the ResourceNotFoundException when the
     * user was not found at the database
     *
     * @param  errorCode  code from messages.properties
     * @param  field  the field used for validation
     * @param  objectName  then name of the object used for validation
     */
    private fun resourceNotFoundException(errorCode: String, field: String, objectName: String) {
        throw ResourceNotFoundException(errorCode, field, objectName)
    }
}