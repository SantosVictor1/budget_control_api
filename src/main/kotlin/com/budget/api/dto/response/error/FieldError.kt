package com.budget.api.dto.response.error

/**
 * Created by Victor Santos on 24/12/2019
 */
class FieldError(
    val errorCode: String,
    val field: String,
    val messsage: String
)