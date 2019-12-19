package com.budget.api.message.response.success

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

class SpentResponse(
    val spentValue: Double?,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val spentDate: Date?,
    val description: String?,
    val user: String?
)