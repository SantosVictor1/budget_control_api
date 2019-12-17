package com.budget.api.service

import com.budget.api.message.request.SpentRequest
import com.budget.api.model.Spent
import com.budget.api.repository.SpentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SpentService {
    @Autowired
    private lateinit var spentRepository: SpentRepository

    fun saveSpent(spent: Spent): Spent {
        return spentRepository.save(spent)
    }

    fun getSpents(): List<Spent> {
        return spentRepository.findAll()
    }

    fun getBydUserId(id: Long): List<Spent> {
        return spentRepository.findAllByUserId(id)
    }
}