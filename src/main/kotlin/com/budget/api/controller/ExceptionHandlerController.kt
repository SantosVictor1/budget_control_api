package com.budget.api.controller

import com.budget.api.message.response.error.ErrorResponse
import com.budget.api.message.response.error.ErrorSupport
import com.budget.api.service.exception.BudgetException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException

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
        return ResponseEntity.status(e.status).body(ErrorResponse(e.status, e.errorsList))
    }
}