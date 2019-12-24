package com.budget.api.message.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Victor Santos on 11/12/2019
 */
class PasswordRequest {
    @NotNull(message = "Senha obrigatória")
    @Size(min = 8, message = "Senha deve ter mais que 8 caracteres")
    val password: String? = null
}