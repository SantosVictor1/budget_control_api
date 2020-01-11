package com.budget.api.exception

/**
 * Created by Victor Santos on 13/12/2019
 */
class BudgetException(
    val status: Int,
    val errorsList: MutableList<String>
) : RuntimeException()