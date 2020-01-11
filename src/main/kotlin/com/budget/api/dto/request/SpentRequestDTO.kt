package com.budget.api.dto.request

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.validator.constraints.br.CPF
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * Created by Victor Santos on 16/12/2019
 */
class SpentRequestDTO(
    spentValue: Double,
    spentDate: Date? = null,
    description: String,
    userCpf: String
) {
    @NotNull(message = "required.value")
    val spentValue: Double = spentValue

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "required.date")
    val spentDate: Date? = spentDate

    @NotBlank(message = "required.description")
    val description: String = description

    @CPF(message = "required.cpf")
    val userCpf: String = userCpf
}