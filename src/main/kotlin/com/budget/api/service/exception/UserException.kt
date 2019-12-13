package com.budget.api.service.exception

import java.lang.RuntimeException

class UserException(
    val status: Int,
    val errorMessage: String
) : RuntimeException() {
}