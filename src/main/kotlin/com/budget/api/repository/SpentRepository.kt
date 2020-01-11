package com.budget.api.repository

import com.budget.api.model.Spent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Created by Victor Santos on 16/12/2019
 */
@Repository
interface SpentRepository : JpaRepository<Spent, Long> {
    /**
     * Method that find all the spending with the specified user's cpf
     *
     * @param  cpf  CPF used in search
     * @return A MutableList with all Spending found
     */
    fun findAllByUserCpf(cpf: String): MutableList<Spent>


    /**
     * Method that returns the sum of all spending's value filtered by
     * user's cpf
     *
     * @param  cpf CPF used in search
     * @return The sum
     */
    @Query("SELECT SUM(spent.spentValue) FROM Spent spent WHERE spent.user.cpf = :cpf")
    fun sumValueByUserCpf(@Param(value = "cpf") cpf: String): Double
}