package com.budget.api.repository

import com.budget.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Victor Santos on 24/11/2019
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {
    /**
     * Method that checks if exists a user with the specified cpf
     * in the system
     *
     * @param  cpf  CPF to check duplicity
     * @return true if exists, false otherwise
     */
    fun existsByEmail(email: String?): Boolean

    /**
     * Method that checks if exists a user with the specified email
     * in the system
     *
     * @param  email  Email to check duplicity
     * @return true if exists, false otherwise
     */
    fun existsByCpf(cpf: String?): Boolean

    /**
     * Method that finds a user by its cpf
     *
     * @param  cpf  CPF used in the search
     * @return The user found or null otherwise
     */
    fun findByCpf(cpf: String): User?
}