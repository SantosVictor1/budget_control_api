package com.budget.api.controller

import com.budget.api.message.response.error.ErrorResponse
import com.budget.api.service.exception.UserException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException


@ControllerAdvice
class ExceptionHandlerController {

    private lateinit var errorResponse: ErrorResponse

    @ExceptionHandler(UserException::class)
    fun validation(e: UserException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        errorResponse = ErrorResponse(e.status, e.errorMessage)

        return ResponseEntity.status(errorResponse.statusCode).body(errorResponse)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun validateCpf(e: ConstraintViolationException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        if (e.localizedMessage.contains("CPF inválido")) {
            errorResponse = ErrorResponse(400, "CPF inválido")
        }

        return ResponseEntity.status(errorResponse.statusCode).body(errorResponse);
    }
}