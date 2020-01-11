package com.budget.api.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * Created by Victor Santos on 11/12/2019
 */
class PasswordRequestDTO(
    password: String
) {
    @NotBlank(message = "required.password")
    @Size(min = 8, message = "invalid.password.size")
    val password: String = password
}