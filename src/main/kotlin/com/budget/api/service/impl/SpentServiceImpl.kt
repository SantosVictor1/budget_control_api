package com.budget.api.service.impl

import com.budget.api.dto.request.SpentRequestDTO
import com.budget.api.dto.response.success.SpentResponseDTO
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
class SpentServiceImpl/* : ISpentService*/ {
//    @Autowired
//    private lateinit var spentRepository: SpentRepository
//    @Autowired
//    private lateinit var userRepository: UserRepository
//    private lateinit var spentsResponseDTOList: MutableList<SpentResponseDTO>
//
//    override fun saveSpent(spentRequestDTO: SpentRequestDTO): SpentResponseDTO {
//        var spent = setSpent(spentRequestDTO)
//
//        spent = spentRepository.save(spent)
//
//        return SpentResponseDTO(spent.spentValue, spent.spentDate, spent.descritpion, spent.user?.name)
//    }
//
//    override fun getSpents(): MutableList<SpentResponseDTO> {
//        spentsResponseDTOList = mutableListOf<SpentResponseDTO>()
//        val spentsList: List<Spent> = spentRepository.findAll()
//
//        spentsList.forEach{
//            val spentResponse = SpentResponseDTO(it.spentValue, it.spentDate, it.descritpion, it.user?.name)
//            spentsResponseDTOList.add(spentResponse)
//        }
//
//        return spentsResponseDTOList
//    }
//
//    override fun getBydUserId(id: Long): MutableList<SpentResponseDTO> {
//        val user = userRepository.findById(id)
//
//        if (!user.isPresent) {
//            throw BudgetException(404, mutableListOf("Usuário não encontrado"))
//        }
//
//        spentsResponseDTOList = mutableListOf<SpentResponseDTO>()
//        val spentsByUserList: List<Spent> = spentRepository.findAllByUserId(id)
//
//        spentsByUserList.forEach{
//            val spentResponse = SpentResponseDTO(it.spentValue, it.spentDate, it.descritpion, it.user?.name)
//            spentsResponseDTOList.add(spentResponse)
//        }
//
//        return spentsResponseDTOList
//    }
//
//    override fun getById(id: Long): SpentResponseDTO {
//        val spent = spentRepository.findById(id)
//
//        if (!spent.isPresent) {
//            throw BudgetException(404, mutableListOf("Gasto não encontrado"))
//        }
//
//        return SpentResponseDTO(spent.get().spentValue, spent.get().spentDate, spent.get().descritpion, spent.get().user?.name)
//    }
//
//    override fun deleteById(id: Long) {
//        val spent = spentRepository.findById(id)
//
//        if (!spent.isPresent) {
//            throw BudgetException(404, mutableListOf("Gasto não encontrado"))
//        }
//
//        spentRepository.deleteById(id)
//    }
//
//    /**
//     * Cria um objeto Spent baseado no DTO SpentRequest
//     *
//     * @param  spentRequestDTO  DTO a ser convertido
//     * @return  retorna o objeto Spent criado
//     */
//    private fun setSpent(spentRequestDTO: SpentRequestDTO): Spent {
//        var spent: Spent = Spent()
//        val user = userRepository.findById(spentRequestDTO.userId!!)
//
//        if (!user.isPresent) {
//            throw BudgetException(404, mutableListOf("Usuário não encontrado"))
//        }
//
//        spent.spentDate = spentRequestDTO.spentDate
//        spent.descritpion = spentRequestDTO.description
//        spent.spentValue = spentRequestDTO.spentValue
//        spent.user = user.get()
//
//        return spent
//    }
}