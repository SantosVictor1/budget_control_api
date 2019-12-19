package com.budget.api.service.exception

class BudgetException(
    val status: Int,
    val errorMessage: String
) : RuntimeException()