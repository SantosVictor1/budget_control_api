package com.budget.api.message.request

import javax.validation.constraints.Size

/**
 * Created by Victor Santos on 11/12/2019
 */
class PasswordRequest (
    @Size(min = 8)
    val password: String
)