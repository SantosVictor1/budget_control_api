package com.budget.api.dto.response.success

import com.budget.api.model.Spent
import com.budget.api.model.User

/**
 * Created by Victor Santos on 04/12/2019
 */
data class UserResponseDTO(
    val id: Long,
    val name: String,
    val email: String,
    val cpf: String,
    val income: Double,
    val spent: MutableList<Spent>?
) {
    companion object {
        fun toDto(user: User): UserResponseDTO {
            return UserResponseDTO(user.id!!, user.name, user.email, user.cpf, user.income, user.spent)
        }
    }
}