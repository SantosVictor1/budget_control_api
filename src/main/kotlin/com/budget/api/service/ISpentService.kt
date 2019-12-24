package com.budget.api.service

import com.budget.api.message.request.SpentRequest
import com.budget.api.message.response.success.SpentResponse

/**
 * Created by Victor Santos on 24/12/2019
 */
interface ISpentService {

    /**
     * Salva gasto de um usuário baseado em seu Id
     *
     * @param  spentRequest  DTO de Spent
     * @return  o DTO SpentRequest
     */
    fun saveSpent(spentRequest: SpentRequest): SpentResponse

    /**
     * Retorna todos os gastos salvos
     *
     * @return MutableList do DTO SpentResponse com os gastos
     */
    fun getSpents(): MutableList<SpentResponse>

    /**
     * Retorna todos os gastos de um usuário
     *
     * @param  id  Id do usuário para filtrar os gastos
     * @return MutableList do DTO SpentResponse com os gastos encontrados
     */
    fun getBydUserId(id: Long): MutableList<SpentResponse>

    /**
     * Retorna um gasto pelo seu Id
     *
     * @param  id  Id do gasto a ser encontrado
     * @return  o DTO SpentResponse do gasto encontrado
     */
    fun getById(id: Long): SpentResponse

    /**
     * Deleta um gasto pelo seu Id
     *
     * @param  id  Id do gasto a ser deletado
     */
    fun deleteById(id: Long)
}