package com.budget.api.controller

import com.budget.api.dto.response.error.ErrorResponse
import com.budget.api.dto.response.error.ErrorSupport
import com.budget.api.exception.BudgetException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

/**
 * Created by Victor Santos on 13/12/2019
 */
@ControllerAdvice
class ExceptionHandlerController {

    private lateinit var errorResponse: ErrorResponse

    /**
     * Trata as exceções do tipo BudgetException
     *
     * @return ResponseEntity do tipo ErrorResponse
     */
    @ExceptionHandler(BudgetException::class)
    fun validation(e: BudgetException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        var errors = mutableListOf<ErrorSupport>()
        e.errorsList.forEach {
            errors.add(ErrorSupport(it))
        }
        return ResponseEntity.status(e.status).body(ErrorResponse(e.status, errors))
    }
}