package com.budget.api.dto.response.success

/**
 * Created by Victor Santos on 04/12/2019
 */
data class UserResponseDTO(
    val id: Long,
    val name: String,
    val email: String,
    val cpf: String,
    val income: Double
//    val spents: List<Spent>?
)