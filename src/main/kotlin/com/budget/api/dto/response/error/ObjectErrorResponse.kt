package com.budget.api.dto.response.error

/**
 * Created by Victor Santos on 04/12/2019
 */
class ObjectErrorResponse(
    val statusHttp: Int,
    val objectName: String,
    val fields: MutableList<FieldError>
)