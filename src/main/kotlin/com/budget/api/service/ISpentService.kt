package com.budget.api.service

import com.budget.api.dto.request.SpentRequestDTO
import com.budget.api.dto.response.success.SpentResponseDTO
import com.budget.api.dto.response.success.SpentSumResponseDTO

/**
 * Created by Victor Santos on 24/12/2019
 */
interface ISpentService {

    /**
     * Method that saves a user's spent based on his cpf
     *
     * @param  spentRequestDTO  Spent's DTO
     * @return  Spent's DTO with the necessary data
     */
    fun saveSpent(spentRequestDTO: SpentRequestDTO): SpentResponseDTO

    /**
     * Method that gets all the spending from an user's cpf
     *
     * @param  cpf  CPF used in search
     * @return  SpentResponseDTO MutableList with all spending found
     */
    fun getByUserCpf(cpf: String): MutableList<SpentResponseDTO>

    /**
     * Method that gets the sum of all spending's value filtered by
     * user's cpf
     *
     * @param  cpf  CPF used in search
     * @return  SpentSumResponseDTO with the sum and the balance
     */
    fun getSpentSumByUserCpf(cpf: String): SpentSumResponseDTO

    /**
     * Method that gets a spent by its Id
     *
     * @param  id  Id used in search
     * @return  Spent's DTO with the necessary data
     */
    fun getById(id: Long): SpentResponseDTO

    /**
     * Method that deletes a spent by its Id
     *
     * @param  id  Id used in search
     */
    fun deleteById(id: Long)
}