package com.budget.api.service.impl

import com.budget.api.common.BudgetErrorCode
import com.budget.api.dto.request.SpentRequestDTO
import com.budget.api.dto.response.success.SpentResponseDTO
import com.budget.api.dto.response.success.SpentSumResponseDTO
import com.budget.api.exception.ResourceNotFoundException
import com.budget.api.model.Spent
import com.budget.api.model.User
import com.budget.api.repository.SpentRepository
import com.budget.api.service.ISpentService
import com.budget.api.service.IUserService
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by Victor Santos on 16/12/2019
 */
@Service
class SpentServiceImpl(
    private val spentRepository: SpentRepository,
    private val userService: IUserService
) : ISpentService {
    private lateinit var spentResponseDTOList: MutableList<SpentResponseDTO>

    override fun saveSpent(spentRequestDTO: SpentRequestDTO): SpentResponseDTO {
        val user = findUserByCpf(spentRequestDTO.userCpf)
        val spent = Spent.toEntity(spentRequestDTO, user)

        return SpentResponseDTO.toDto(spentRepository.save(spent))
    }

    override fun getByUserCpf(cpf: String): MutableList<SpentResponseDTO> {
        findUserByCpf(cpf)
        val spentByUserList: List<Spent> = spentRepository.findAllByUserCpf(cpf)

        spentResponseDTOList = mutableListOf()

        spentByUserList.forEach {
            spentResponseDTOList.add(SpentResponseDTO.toDto(it))
        }

        return spentResponseDTOList
    }

    override fun getSpentSumByUserCpf(cpf: String): SpentSumResponseDTO {
        val user = findUserByCpf(cpf)
        val spentSum = spentRepository.sumValueByUserCpf(cpf)
        val balance = user.income - spentSum

        return SpentSumResponseDTO(spentSum, balance, cpf)
    }

    override fun getById(id: String): SpentResponseDTO {
        val spent = spentRepository.findById(id)

        if (!spent.isPresent) {
            resourceNotFoundException(BudgetErrorCode.BUDGET101.code, "id", Spent.javaClass.canonicalName)
        }

        return SpentResponseDTO.toDto(spent.get())
    }

    override fun deleteById(id: String) {
        val spent = spentRepository.findById(id)

        if (!spent.isPresent) {
            resourceNotFoundException(BudgetErrorCode.BUDGET101.code, "id", Spent.javaClass.canonicalName)
        }

        spentRepository.deleteById(id)
    }

    /**
     * Method that calls the getByCpf method from userService
     *
     * @param  cpf  CPF used in the search
     * @return The user found
     */
    private fun findUserByCpf(cpf: String): User {
        return User.fromUserResponseToEntity(userService.getByCpf(cpf))
    }

    /**
     * Method that throws the ResourceNotFoundException when the
     * spent was not found at the database
     *
     * @param  errorCode  code from messages.properties
     * @param  field  the field used for validation
     * @param  objectName  then name of the object used for validation
     */
    private fun resourceNotFoundException(errorCode: String, field: String, objectName: String) {
        throw ResourceNotFoundException(errorCode, field, objectName)
    }
}