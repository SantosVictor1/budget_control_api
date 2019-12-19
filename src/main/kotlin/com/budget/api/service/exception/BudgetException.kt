package com.budget.api.service.exception

import java.lang.RuntimeException

class BudgetException(
    val status: Int,
    val errorMessage: String
) : RuntimeException()