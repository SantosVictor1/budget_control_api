package com.budget.api.dto.response.success

import com.budget.api.model.User

/**
 * Created by Victor Santos on 11/01/2020
 */
class UserSpentResponseDTO (
    val id: Long,
    val name: String,
    val cpf: String
) {
    companion object {
        fun toDto(user: User): UserSpentResponseDTO {
            return UserSpentResponseDTO(user.id!!, user.name, user.cpf)
        }
    }
}