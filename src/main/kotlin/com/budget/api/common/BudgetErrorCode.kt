package com.budget.api.common

/**
 * Created by Victor Santos on 11/01/2020
 */
class BudgetErrorCode {
    val key: String
    val code: String

    private constructor(key: String, code: String) {
        this.key = key
        this.code = code
    }

    companion object {
        //User Errors
        val BUDGET001: BudgetErrorCode = BudgetErrorCode("BUDGET-001", "required.name")
        val BUDGET002: BudgetErrorCode = BudgetErrorCode("BUDGET-002", "invalid.name.size")
        val BUDGET003: BudgetErrorCode = BudgetErrorCode("BUDGET-003", "required.email")
        val BUDGET004: BudgetErrorCode = BudgetErrorCode("BUDGET-004", "invalid.email")
        val BUDGET005: BudgetErrorCode = BudgetErrorCode("BUDGET-005", "required.cpf")
        val BUDGET006: BudgetErrorCode = BudgetErrorCode("BUDGET-006", "invalid.cpf")
        val BUDGET007: BudgetErrorCode = BudgetErrorCode("BUDGET-007", "invalid.cpf")
        val BUDGET008: BudgetErrorCode = BudgetErrorCode("BUDGET-008", "required.password")
        val BUDGET009: BudgetErrorCode = BudgetErrorCode("BUDGET-009", "invalid.password.size")
        val BUDGET010: BudgetErrorCode = BudgetErrorCode("BUDGET-010", "required.income")
        val BUDGET011: BudgetErrorCode = BudgetErrorCode("BUDGET-011", "duplicated.cpf")
        val BUDGET012: BudgetErrorCode = BudgetErrorCode("BUDGET-012", "duplicated.email")
    }
}