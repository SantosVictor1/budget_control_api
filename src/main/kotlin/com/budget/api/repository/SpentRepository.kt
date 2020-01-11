package com.budget.api.repository

import com.budget.api.model.Spent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Victor Santos on 16/12/2019
 */
@Repository
interface SpentRepository : JpaRepository<Spent, Long> {
    /**
     * Method that find all the spents with the specified user's cpf
     *
     * @param  cpf  CPF used in search
     * @return A MutableList with all Spents found
     */
    fun findAllByUserCpf(cpf: String): MutableList<Spent>
}