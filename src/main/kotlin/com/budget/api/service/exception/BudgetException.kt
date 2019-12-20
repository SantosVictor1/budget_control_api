package com.budget.api.service.exception

/**
 * Created by Victor Santos on 13/12/2019
 */
class BudgetException(
    val status: Int,
    val errorMessage: String
) : RuntimeException()