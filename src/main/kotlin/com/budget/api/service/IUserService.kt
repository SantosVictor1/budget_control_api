package com.budget.api.service

import com.budget.api.dto.request.PasswordRequestDTO
import com.budget.api.dto.request.UserRequestDTO
import com.budget.api.dto.response.success.SuccessResponseDTO
import com.budget.api.dto.response.success.UserResponseDTO
import com.budget.api.model.User
import java.util.*

/**
 * Created by Victor Santos on 24/12/2019
 */
interface IUserService {

    /**
     * Método responsável por cadastrar o usuário
     *
     * @param  userRequestDTO  DTO de User
     * @return O usuário criado
     */
    fun createUser(userRequestDTO: UserRequestDTO): UserResponseDTO

    /**
     * Método responsável por atualizar a senha de algum usuário
     *
     * @param  user  com a nova senha
     * @return DTO de sucesso
     */
    fun updateUser(passwordRequestDTO: PasswordRequestDTO): SuccessResponseDTO

    /**
     * Método responsável por listar todos os usuários cadastrados
     *
     * @return Lista dos usuários cadastrados
     */
    fun getAll(): MutableList<UserResponseDTO>

    /**
     * Método responsável por listar o usuário pelo Id
     *
     * @param  id  Id do usuário a ser encontrado
     * @return Optional do tipo User
     */
    fun getById(id: Long): UserResponseDTO

    /**
     * Método responsável por listar o usuário pelo CPF
     *
     * @param  cpf  CPF do usuário a ser encontrado
     * @return Optional do tipo User
     */
    fun getByCpf(cpf: String): UserResponseDTO

    /**
     * Método responsável por usuário pelo Id
     *
     * @param  id  Id do usuário a ser deletado
     */
    fun deleteByCpf(cpf: String)
}