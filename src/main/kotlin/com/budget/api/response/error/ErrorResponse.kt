package com.budget.api.response.error

import com.budget.api.response.Response

class ErrorResponse(
        val statusCode: Int,
        val message: String
) : Response