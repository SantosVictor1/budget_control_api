package com.budget.api.message.request

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class SpentRequest(
    @NotNull(message = "Valor obrigatório")
    val spentValue: Double?,
    
    @JsonFormat(pattern = "dd/MM/yyy")
    val spentDate: Date?,

    @NotBlank(message = "Local obrigatório")
    val description: String?,

    @NotNull(message ="UserId obrigatório")
    val userId: Long
)