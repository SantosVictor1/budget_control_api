package com.budget.api.service.impl

import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import com.budget.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun existsByIdAndEmailOrCpf(email: String?, cpf: String?): Boolean {
        return userRepository.existsByEmailOrCpf(email, cpf)
    }

    override fun save(user: User): User {
        return userRepository.save(user)
    }
}