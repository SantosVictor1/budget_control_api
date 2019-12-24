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

    private lateinit var userRequest: UserRequest
    private lateinit var user: User

    @Before
    fun setUser() {
        userRequest = UserRequest(
            userName = "Carolina",
            userEmail = "carolina@gmail.com",
            userCpf = "24572543615",
            userPassword= "123456789",
            userIncome =  2000.0
        )

        user = User()
        user.income = 2000.0
        user.password = "123456789"
        user.email = "carolina@gmail.com"
        user.cpf = "96807865015"
        user.spents = null
    }

    @Test(expected = BudgetException::class)
    fun existsByEmailTest() {
        Mockito.`when`(userRepository.existsByEmail(userRequest.email)).thenReturn(true)

        userService.createUser(userRequest)

        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail(userRequest.email)
    }

    @Test(expected = BudgetException::class)
    fun existsByCpfTest() {
        Mockito.`when`(userRepository.existsByCpf(userRequest.cpf)).thenReturn(true)

        userService.createUser(userRequest)

        Mockito.verify(userRepository, Mockito.times(1)).existsByCpf(userRequest.cpf)
    }
}