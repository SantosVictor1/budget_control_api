package com.budget.api.repository

import com.budget.api.model.Spent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpentRepository : JpaRepository<Spent, Long> {
    fun findAllByUserId(id: Long): List<Spent>
}