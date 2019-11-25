package com.budget.api.repository

import com.budget.api.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String?): Boolean
    fun existsByCpf(cpf: String?): Boolean
}