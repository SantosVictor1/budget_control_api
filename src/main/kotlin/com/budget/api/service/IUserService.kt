package com.budget.api.service

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
    fun updateUser(user: User): SuccessResponseDTO

    /**
     * Método responsável por listar todos os usuários cadastrados
     *
     * @return Lista dos usuários cadastrados
     */
    fun getAll(): List<User>

    /**
     * Método responsável por listar o usuário pelo Id
     *
     * @param  id  do usuário a ser encontrado
     * @return Optional do tipo User
     */
    fun getById(id: Long): Optional<User>

    /**
     * Método responsável por usuário pelo Id
     *
     * @param  id  do usuário a ser deletado
     */
    fun deleteById(id: Long)

    /**
     * Método responsável por criar um objeto User
     *
     * @param userRequestDTO DTO que será usado para criar um User
     *
     * @return User criado
     */
    fun setUser(userRequestDTO: UserRequestDTO): User
}