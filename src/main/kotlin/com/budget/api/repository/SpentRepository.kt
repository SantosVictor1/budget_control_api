package com.budget.api.repository

import com.budget.api.model.Spent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Created by Victor Santos on 16/12/2019
 */
@Repository
interface SpentRepository : JpaRepository<Spent, String> {
    /**
     * Method that find all the spending with the specified user's cpf
     * within a time interval
     *
     * @param  cpf  CPF used in search
     * @return A MutableList with all Spending found
     */
    @Query(value = "SELECT spent FROM Spent spent WHERE spent.user.cpf = :cpf AND spent.spentDate BETWEEN :initialDate AND :finalDate ORDER BY spent.spentDate DESC")
    fun findAllByUserCpf(
        @Param(value = "cpf") cpf: String,
        @Param(value ="initialDate") initialDate: Date,
        @Param(value ="finalDate") finalDate: Date
    ): MutableList<Spent>


    /**
     * Method that returns the sum of all spending's value within a time
     * interval filtered by user's cpf
     *
     * @param  cpf CPF used in search
     * @return The sum
     */
    @Query(value = "SELECT SUM(spent.spentValue) FROM Spent spent WHERE spent.user.cpf = :cpf AND spent.spentDate BETWEEN :initialDate AND :finalDate")
    fun sumValueByUserCpf(
        @Param(value = "cpf") cpf: String,
        @Param(value ="initialDate") initialDate: Date,
        @Param(value ="finalDate") finalDate: Date
    ): Double?
}