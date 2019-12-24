package com.budget.api.service

import com.budget.api.message.request.UserRequest
import com.budget.api.message.response.success.SuccessResponse
import com.budget.api.message.response.success.UserResponse
import com.budget.api.model.User
import java.util.*

/**
 * Created by Victor Santos on 24/12/2019
 */
interface IUserService {

    /**
     * Método responsável por cadastrar o usuário
     *
     * @param  userRequest  DTO de User
     * @return O usuário criado
     */
    fun createUser(userRequest: UserRequest): UserResponse

    /**
     * Método responsável por atualizar a senha de algum usuário
     *
     * @param  user  com a nova senha
     * @return DTO de sucesso
     */
    fun updateUser(user: User): SuccessResponse

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
}