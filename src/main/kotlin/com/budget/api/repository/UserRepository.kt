package com.budget.api.repository

import com.budget.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String?): Boolean
    fun existsByCpf(cpf: String?): Boolean
}