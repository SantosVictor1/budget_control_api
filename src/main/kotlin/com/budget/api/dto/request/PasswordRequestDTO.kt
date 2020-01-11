package com.budget.api.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * Created by Victor Santos on 11/12/2019
 */
class PasswordRequestDTO(
    password: String
) {
    @NotBlank(message = "Senha obrigatória")
    @Size(min = 8, message = "Senha deve ter mais que 8 caracteres")
    val password: String = password
}