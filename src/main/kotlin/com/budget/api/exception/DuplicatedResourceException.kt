package com.budget.api.exception

/**
 * Created by Victor Santos on 11/01/2020
 */
class DuplicatedResourceException(
    errorCode: String,
    field: String,
    objectName: String
) : BudgetException(errorCode, field, objectName)