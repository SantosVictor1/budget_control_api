package com.budget.api.message.request

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class SpentRequest(
    @NotNull(message = "Valor obrigatório")
    val spentValue: Double? = null,

//    @NotEmpty(message = "Data obrigatória")
//    @DateTimeFormat(pattern = "dd/MM/yyy")
//    val spentDate: Date? = null,

    @NotBlank(message = "Local obrigatório")
    val spentPlace: String? = null,

    @NotNull(message ="UserId obrigatório")
    val userId: Long
)