package com.budget.api.service.exception

import com.budget.api.message.response.error.ErrorSupport

/**
 * Created by Victor Santos on 13/12/2019
 */
class BudgetException(
    val status: Int,
    val errorsList: MutableList<String>
) : RuntimeException()