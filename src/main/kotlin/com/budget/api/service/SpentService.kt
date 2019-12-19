package com.budget.api.service

import com.budget.api.message.request.SpentRequest
import com.budget.api.message.response.success.SpentResponse
import com.budget.api.message.response.success.UserResponse
import com.budget.api.model.Spent
import com.budget.api.model.User
import com.budget.api.repository.SpentRepository
import com.budget.api.repository.UserRepository
import com.budget.api.service.exception.BudgetException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SpentService {
    @Autowired
    private lateinit var spentRepository: SpentRepository
    @Autowired
    private lateinit var userRepository: UserRepository
    private lateinit var spentsResponseList: MutableList<SpentResponse>

    fun saveSpent(spentRequest: SpentRequest): Spent {
        val spent = setSpent(spentRequest)
        validateFields(spentRequest)

        return spentRepository.save(spent)
    }

    fun getSpents(): MutableList<SpentResponse> {
        spentsResponseList = mutableListOf<SpentResponse>()
        val spentsList: List<Spent> = spentRepository.findAll()

        spentsList.forEach{
            val spentResponse = SpentResponse(it.spentValue, it.spentDate, it.descritpion, it.user?.name)
            spentsResponseList.add(spentResponse)
        }

        return spentsResponseList
    }

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

    fun getById(id: Long): SpentResponse {
        val spent = spentRepository.findById(id)

        if (!spent.isPresent) {
            throw BudgetException(404, "Gasto não encontrado")
        }

        return SpentResponse(spent.get().spentValue, spent.get().spentDate, spent.get().descritpion, spent.get().user?.name)
    }

    fun deleteById(id: Long) {
        val spent = spentRepository.findById(id)

        if (!spent.isPresent) {
            throw BudgetException(404, "Gasto não encontrado")
        }

        spentRepository.deleteById(id)
    }

    private fun setSpent(spentRequest: SpentRequest): Spent {
        var spent: Spent = Spent()
        val user: User = userRepository.findById(spentRequest.userId).get()

        spent.spentDate = spentRequest.spentDate
        spent.descritpion = spentRequest.description
        spent.spentValue = spentRequest.spentValue
        spent.user = user

        return spent
    }

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