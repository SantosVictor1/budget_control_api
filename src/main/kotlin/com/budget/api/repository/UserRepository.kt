package com.budget.api.repository

import com.budget.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Victor Santos on 24/11/2019
 */
@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String?): Boolean
    fun existsByCpf(cpf: String?): Boolean
}