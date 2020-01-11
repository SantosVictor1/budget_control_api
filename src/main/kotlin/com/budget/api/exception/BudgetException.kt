package com.budget.api.exception

/**
 * Created by Victor Santos on 13/12/2019
 */
open class BudgetException(
    val errorCode: String,
    val field: String,
    val objectName: String
) : RuntimeException()