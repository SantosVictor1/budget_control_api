package com.budget.api.controller

import com.budget.api.common.MessageResource
import com.budget.api.dto.response.error.FieldError
import com.budget.api.dto.response.error.ObjectErrorResponse
import com.budget.api.exception.DuplicatedResourceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Created by Victor Santos on 13/12/2019
 */
@ControllerAdvice
class ExceptionHandlerController(
    val messageResource: MessageResource
) {
//    /**
//     * Trata as exceções do tipo BudgetException
//     *
//     * @return ResponseEntity do tipo ErrorResponse
//     */
//    @ExceptionHandler(BudgetException::class)
//    fun validation(e: BudgetException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
//        var errors = mutableListOf<ErrorSupport>()
//        e.errorsList.forEach {
//            errors.add(ErrorSupport(it))
//        }
//        return ResponseEntity.status(e.status).body(ErrorResponse(e.status, errors))
//    }

    @ExceptionHandler(DuplicatedResourceException::class)
    fun handleDuplicatedResourceException(e: DuplicatedResourceException): ResponseEntity<ObjectErrorResponse> {
        val errorMessage = messageResource.getMessage(e.errorCode)
        val fields = mutableListOf<FieldError>(FieldError(e.errorCode, e.field, errorMessage))

        val objectErrorResponse = getObjectErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, e.objectName, fields)

        return ResponseEntity(objectErrorResponse, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ObjectErrorResponse> {
        val fields: MutableList<FieldError> = mutableListOf()

        e.bindingResult.fieldErrors.forEach {
            val errorMessage = messageResource.getMessage(it.defaultMessage!!)
            fields.add(FieldError(it.defaultMessage.toString(), it.field, errorMessage))
        }

        val objectErrorResponse = getObjectErrorResponse(HttpStatus.BAD_REQUEST, e.bindingResult.objectName, fields)

        return ResponseEntity.badRequest().body(objectErrorResponse)
    }

    private fun getObjectErrorResponse(
        httpStatus: HttpStatus,
        objectName: String,
        fields: MutableList<FieldError>
    ): ObjectErrorResponse {
        return ObjectErrorResponse(httpStatus.value(), objectName, fields)
    }
}