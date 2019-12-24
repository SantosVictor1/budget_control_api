package com.budget.api.message.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * Created by Victor Santos on 16/12/2019
 */
class SpentRequest {
    @NotNull(message = "Valor obrigatório")
    val spentValue: Double? = null

    @JsonFormat(pattern = "dd/MM/yyy")
    @NotNull(message = "Data obrigatória")
    val spentDate: Date? = null

    @NotNull(message = "Local obrigatório")
    val description: String? = null

    @NotNull(message = "UserId obrigatório")
    val userId: Long? = null
}