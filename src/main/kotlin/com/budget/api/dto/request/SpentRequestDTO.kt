package com.budget.api.dto.request

import java.util.*
import javax.validation.constraints.NotNull

/**
 * Created by Victor Santos on 16/12/2019
 */
class SpentRequestDTO(
    spentValue: Double,
    spentDate: Date,
    description: String,
    userCpf: String
) {
    @NotNull(message = "Valor obrigatório")
    val spentValue: Double = spentValue

    @NotNull(message = "Data obrigatória")
    val spentDate: Date = spentDate

    @NotNull(message = "Local obrigatório")
    val description: String = description

    @NotNull(message = "UserId obrigatório")
    val userCpf: String = userCpf
}