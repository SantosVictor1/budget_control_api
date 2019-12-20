package com.budget.api.repository

import com.budget.api.model.Spent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Victor Santos on 16/12/2019
 */
@Repository
interface SpentRepository : JpaRepository<Spent, Long> {
    fun findAllByUserId(id: Long): List<Spent>
}