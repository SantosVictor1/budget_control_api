package com.budget.api.dto.request

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.NotNull

/**
 * Created by Victor Santos on 11/01/2020
 */
class IncomeRequestDTO (
    income: Double,
    cpf: String
) {
    @NotNull(message = "required.income")
    val income: Double = income

    @CPF(message = "invalid.cpf")
    val cpf: String = cpf
}