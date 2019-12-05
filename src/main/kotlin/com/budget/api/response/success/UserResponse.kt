package com.budget.api.response.success

import com.budget.api.response.Response

class UserResponse(
        val id: Long?,
        val name: String?,
        val email: String?,
        val cpf: String?
) : Response {}