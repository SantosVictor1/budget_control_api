package com.budget.api.dto.request

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Victor Santos on 06/12/2019
 */
class UserRequestDTO (
    name: String,
    email: String,
    cpf: String,
    password: String,
    income: Double
) {
    @NotBlank(message = "required.name")
    @Size(min = 3, message = "invalid.name.size")
    val name: String = name

    @NotBlank(message = "required.email")
    @Email(message = "invalid.email")
    val email: String = email

    @NotBlank(message = "required.cpf")
    @CPF(message = "invalid.cpf")
    val cpf: String = cpf

    @NotBlank(message = "required.password")
    @Size(min = 8, message = "invalid.password.size")
    val password: String = password

    @NotNull(message = "required.income")
    val income: Double = income
}