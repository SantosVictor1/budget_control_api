package com.budget.api.message.response.error

/**
 * Created by Victor Santos on 04/12/2019
 */
class ErrorResponse(
    val httpStatus: Int,
    val errors: MutableList<ErrorSupport>
)