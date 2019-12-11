package com.budget.api.message.request

import javax.validation.constraints.Size

class PasswordRequest (
    @Size(min = 8)
    val password: String
)