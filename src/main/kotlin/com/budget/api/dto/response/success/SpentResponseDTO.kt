package com.budget.api.dto.response.success

import com.budget.api.model.Spent
import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

/**
 * Created by Victor Santos on 16/12/2019
 */
class SpentResponseDTO(
    val spentValue: Double,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val spentDate: Date,
    val description: String,
    val user: UserSpentResponseDTO
) {
    companion object {
        fun toDto(spent: Spent): SpentResponseDTO {
            val user = UserSpentResponseDTO.toDto(spent.user!!)
            return SpentResponseDTO(spent.spentValue, spent.spentDate, spent.description, user)
        }
    }
}