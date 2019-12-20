package com.budget.api.message.response.success

import com.budget.api.model.Spent

/**
 * Created by Victor Santos on 04/12/2019
 */
class UserResponse(
    val id: Long?,
    val name: String?,
    val email: String?,
    val cpf: String?,
    val income: Double?,
    val spents: List<Spent>?
)