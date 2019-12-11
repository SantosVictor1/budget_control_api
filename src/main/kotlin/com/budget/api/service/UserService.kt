package com.budget.api.service

import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun existsByEmail(email: String?): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun existsByCpf(cpf: String?): Boolean {
        return userRepository.existsByCpf(cpf)
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    fun getById(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    fun deleteById(id: Long) {
        return userRepository.deleteById(id)
    }
}