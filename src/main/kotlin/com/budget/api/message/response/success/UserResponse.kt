package com.budget.api.message.response.success

import com.budget.api.model.Spent

class UserResponse(
        val id: Long?,
        val name: String?,
        val email: String?,
        val cpf: String?,
        val income: Double?,
        val spents: List<Spent>?
)