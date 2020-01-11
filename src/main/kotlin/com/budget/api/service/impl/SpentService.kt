package com.budget.api.service.impl

import com.budget.api.message.request.SpentRequest
import com.budget.api.message.response.success.SpentResponse
import com.budget.api.model.Spent
import com.budget.api.repository.SpentRepository
import com.budget.api.repository.UserRepository
import com.budget.api.service.ISpentService
import com.budget.api.exception.BudgetException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Victor Santos on 16/12/2019
 */
@Service
class SpentService : ISpentService {
    @Autowired
    private lateinit var spentRepository: SpentRepository
    @Autowired
    private lateinit var userRepository: UserRepository
    private lateinit var spentsResponseList: MutableList<SpentResponse>

    override fun saveSpent(spentRequest: SpentRequest): SpentResponse {
        var spent = setSpent(spentRequest)

        spent = spentRepository.save(spent)

        return SpentResponse(spent.spentValue, spent.spentDate, spent.descritpion, spent.user?.name)
    }

    override fun getSpents(): MutableList<SpentResponse> {
        spentsResponseList = mutableListOf<SpentResponse>()
        val spentsList: List<Spent> = spentRepository.findAll()

        spentsList.forEach{
            val spentResponse = SpentResponse(it.spentValue, it.spentDate, it.descritpion, it.user?.name)
            spentsResponseList.add(spentResponse)
        }

        return spentsResponseList
    }

    override fun getBydUserId(id: Long): MutableList<SpentResponse> {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw BudgetException(404, mutableListOf("Usuário não encontrado"))
        }

        spentsResponseList = mutableListOf<SpentResponse>()
        val spentsByUserList: List<Spent> = spentRepository.findAllByUserId(id)

        spentsByUserList.forEach{
            val spentResponse = SpentResponse(it.spentValue, it.spentDate, it.descritpion, it.user?.name)
            spentsResponseList.add(spentResponse)
        }

        return spentsResponseList
    }

    override fun getById(id: Long): SpentResponse {
        val spent = spentRepository.findById(id)

        if (!spent.isPresent) {
            throw BudgetException(404, mutableListOf("Gasto não encontrado"))
        }

        return SpentResponse(spent.get().spentValue, spent.get().spentDate, spent.get().descritpion, spent.get().user?.name)
    }

    override fun deleteById(id: Long) {
        val spent = spentRepository.findById(id)

        if (!spent.isPresent) {
            throw BudgetException(404, mutableListOf("Gasto não encontrado"))
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
        val user = userRepository.findById(spentRequest.userId!!)

        if (!user.isPresent) {
            throw BudgetException(404, mutableListOf("Usuário não encontrado"))
        }

        spent.spentDate = spentRequest.spentDate
        spent.descritpion = spentRequest.description
        spent.spentValue = spentRequest.spentValue
        spent.user = user.get()

        return spent
    }
}