package com.budget.api

import com.budget.api.message.request.UserRequest
import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import com.budget.api.service.exception.BudgetException
import com.budget.api.service.impl.UserService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by Victor Santos on 24/12/2019
 */
@RunWith(SpringRunner::class)
class UserServiceTest {
    @InjectMocks
    private lateinit var userService: UserService

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var user: User

    @Before
    fun setUser() {
        user = User()
        user.income = 2000.0
        user.password = "123456789"
        user.email = "carolina@gmail.com"
        user.cpf = "24572543615"
        user.spents = null
    }

    @Test(expected = BudgetException::class)
    fun existsByEmailTest() {
        Mockito.`when`(userRepository.existsByEmail(user.email)).thenReturn(true)

        userService.createUser(user)

        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail(user.email)
    }

    @Test(expected = BudgetException::class)
    fun existsByCpfTest() {
        Mockito.`when`(userRepository.existsByCpf(user.cpf)).thenReturn(true)

        userService.createUser(user)

        Mockito.verify(userRepository, Mockito.times(1)).existsByCpf(user.cpf)
    }
}