package com.budget.api.service

import com.budget.api.dto.request.SpentRequestDTO
import com.budget.api.dto.response.success.SpentResponseDTO

/**
 * Created by Victor Santos on 24/12/2019
 */
interface ISpentService {

    /**
     * Salva gasto de um usuário baseado em seu Id
     *
     * @param  spentRequestDTO  DTO de Spent
     * @return  o DTO SpentRequest
     */
    fun saveSpent(spentRequestDTO: SpentRequestDTO): SpentResponseDTO

    /**
     * Retorna todos os gastos salvos
     *
     * @return MutableList do DTO SpentResponse com os gastos
     */
    fun getSpents(): MutableList<SpentResponseDTO>

    /**
     * Retorna todos os gastos de um usuário
     *
     * @param  id  Id do usuário para filtrar os gastos
     * @return MutableList do DTO SpentResponse com os gastos encontrados
     */
    fun getBydUserId(id: Long): MutableList<SpentResponseDTO>

    /**
     * Retorna um gasto pelo seu Id
     *
     * @param  id  Id do gasto a ser encontrado
     * @return  o DTO SpentResponse do gasto encontrado
     */
    fun getById(id: Long): SpentResponseDTO

    /**
     * Deleta um gasto pelo seu Id
     *
     * @param  id  Id do gasto a ser deletado
     */
    fun deleteById(id: Long)
}