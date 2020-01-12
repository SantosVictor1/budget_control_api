package com.budget.api.dto.response.success

import com.budget.api.model.User

/**
 * Created by Victor Santos on 11/01/2020
 */
data class UserIncomeResponseDTO (
    val id: Long,
    val name: String,
    val email: String,
    val cpf: String,
    val income: Double
) {
    companion object {
        fun toDto(user: User): UserIncomeResponseDTO {
            return UserIncomeResponseDTO(user.id!!, user.name, user.email, user.cpf, user.income)
        }
    }
}