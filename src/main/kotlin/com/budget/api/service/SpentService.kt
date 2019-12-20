package com.budget.api.service

import com.budget.api.message.request.SpentRequest
import com.budget.api.message.response.success.SpentResponse
import com.budget.api.model.Spent
import com.budget.api.model.User
import com.budget.api.repository.SpentRepository
import com.budget.api.repository.UserRepository
import com.budget.api.service.exception.BudgetException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Victor Santos on 16/12/2019
 */
@Service
class SpentService {
    @Autowired
    private lateinit var spentRepository: SpentRepository
    @Autowired
    private lateinit var userRepository: UserRepository
    private lateinit var spentsResponseList: MutableList<SpentResponse>

    /**
     * Salva gasto de um usuário baseado em seu Id
     *
     * @param  spentRequest  DTO de Spent
     * @return  o DTO SpentRequest
     */
    fun saveSpent(spentRequest: SpentRequest): SpentResponse {
        var spent = setSpent(spentRequest)
        validateFields(spentRequest)

        spent = spentRepository.save(spent)

        return SpentResponse(spent.spentValue, spent.spentDate, spent.descritpion, spent.user?.name)
    }

    /**
     * Retorna todos os gastos salvos
     *
     * @return MutableList do DTO SpentResponse com os gastos
     */
    fun getSpents(): MutableList<SpentResponse> {
        spentsResponseList = mutableListOf<SpentResponse>()
        val spentsList: List<Spent> = spentRepository.findAll()

        spentsList.forEach{
            val spentResponse = SpentResponse(it.spentValue, it.spentDate, it.descritpion, it.user?.name)
            spentsResponseList.add(spentResponse)
        }

        return spentsResponseList
    }

    /**
     * Retorna todos os gastos de um usuário
     *
     * @param  id  Id do usuário para filtrar os gastos
     * @return MutableList do DTO SpentResponse com os gastos encontrados
     */
    fun getBydUserId(id: Long): MutableList<SpentResponse> {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw BudgetException(404, "Usuário não encontrado")
        }

        spentsResponseList = mutableListOf<SpentResponse>()
        val spentsByUserList: List<Spent> = spentRepository.findAllByUserId(id)

        spentsByUserList.forEach{
            val spentResponse = SpentResponse(it.spentValue, it.spentDate, it.descritpion, it.user?.name)
            spentsResponseList.add(spentResponse)
        }

        return spentsResponseList
    }


    /**
     * Retorna um gasto pelo seu Id
     *
     * @param  id  Id do gasto a ser encontrado
     * @return  o DTO SpentResponse do gasto encontrado
     */
    fun getById(id: Long): SpentResponse {
        val spent = spentRepository.findById(id)

        if (!spent.isPresent) {
            throw BudgetException(404, "Gasto não encontrado")
        }

        return SpentResponse(spent.get().spentValue, spent.get().spentDate, spent.get().descritpion, spent.get().user?.name)
    }

    /**
     * Deleta um gasto pelo seu Id
     *
     * @param  id  Id do gasto a ser deletado
     */
    fun deleteById(id: Long) {
        val spent = spentRepository.findById(id)

        if (!spent.isPresent) {
            throw BudgetException(404, "Gasto não encontrado")
        }

        spentRepository.deleteById(id)
    }

    /**
     * Cria um objeto Spent baseado no DTO SpentRequest
     *
     * @param  spentRequest  DTO a ser convertido
     * @return  retorna o objeto Spent criado
     */
    private fun setSpent(spentRequest: SpentRequest): Spent {
        var spent: Spent = Spent()
        val user: User = userRepository.findById(spentRequest.userId).get()

        spent.spentDate = spentRequest.spentDate
        spent.descritpion = spentRequest.description
        spent.spentValue = spentRequest.spentValue
        spent.user = user

        return spent
    }


    /**
     * Valida os campos enviados para cadastro
     *
     * @param  spentRequest  DTO que será validado
     */
    private fun validateFields(spentRequest: SpentRequest) {
        if (spentRequest.description?.isEmpty()!!) {
            throw BudgetException(400, "Descrição é obrigatória")
        }

        if (spentRequest.spentDate == null) {
            throw BudgetException(400, "Data é obrigatória")
        }

        if (spentRequest.spentValue == null) {
            throw BudgetException(400, "Valor gasto é obrigatório")
        }
    }
}