package com.budget.api.service

import com.budget.api.dto.request.IncomeRequestDTO
import com.budget.api.dto.request.PasswordRequestDTO
import com.budget.api.dto.request.UserRequestDTO
import com.budget.api.dto.response.success.SuccessResponseDTO
import com.budget.api.dto.response.success.UserIncomeResponseDTO
import com.budget.api.dto.response.success.UserResponseDTO
import java.util.*

/**
 * Created by Victor Santos on 24/12/2019
 */
interface IUserService {

    /**
     * Method that validates and register a user
     *
     * @param  userRequestDTO  User's DTO received in the request
     * @return A DTO with user's data without password
     */
    fun createUser(userRequestDTO: UserRequestDTO): UserResponseDTO

    /**
     * Method that updates a user's password
     *
     * @param  passwordRequestDTO  DTO containing the new password and the
     * user's cpf
     * @return SuccessResponseDTO which contains only the http status
     */
    fun updateUserPassword(passwordRequestDTO: PasswordRequestDTO): SuccessResponseDTO

    /**
     * Method that updates a user's income
     *
     * @param  incomeRequestDTO  DTO containing the new income and the
     * user's cpf
     * @return UserIncomeResponseDTO which contains all the user's data
     */
    fun updateUserIncome(incomeRequestDTO: IncomeRequestDTO): UserIncomeResponseDTO

    /**
     * Method that gets all the users registered
     *
     * @return The List of all users
     */
    fun getAll(): MutableList<UserResponseDTO>

    /**
     * Method that get the user by its Id
     *
     * @param  id  Id used in the search
     * @return A DTO with user's data without password
     */
    fun getById(id: String): UserResponseDTO

    /**
     * Method that get the user by its cpf
     *
     * @param  cpf  CPF used in the search
     * @return A DTO with user's data without password
     */
    fun getByCpf(cpf: String): UserResponseDTO

    /**
     * Method that deletes a user using its cpf in the search
     *
     * @param  cpf  CPF used in the search
     */
    fun deleteByCpf(cpf: String)
}