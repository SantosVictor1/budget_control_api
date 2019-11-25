package com.budget.api.service

import com.budget.api.model.User
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun existsByIdAndEmailOrCpf(email: String?): Boolean
    fun save(user: User): User
}