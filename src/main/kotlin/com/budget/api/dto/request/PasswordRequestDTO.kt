package com.budget.api.dto.request

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * Created by Victor Santos on 11/12/2019
 */
class PasswordRequestDTO(
    cpf: String,
    password: String
) {
    @CPF(message = "invalid.cpf")
    val cpf: String = cpf

    @NotBlank(message = "required.password")
    @Size(min = 8, message = "invalid.password.size")
    val password: String = password
}