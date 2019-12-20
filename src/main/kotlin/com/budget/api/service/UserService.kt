package com.budget.api.service

import com.budget.api.message.request.UserRequest
import com.budget.api.model.User
import com.budget.api.repository.UserRepository
import com.budget.api.service.exception.BudgetException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Victor Santos on 25/11/2019
 */
@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    /**
     * Método responsável por verificar se o cpf já existe
     *
     * @param  cpf  CPF para verificar duplicidade
     * @return true se já existir o CPF
     */
    fun existsByCpf(cpf: String): Boolean {
        return userRepository.existsByCpf(cpf)
    }

    /**
     * Método responsável por verificar se o email já existe
     *
     * @param  email  Email para verificar duplicidade
     * @return true se o email já existir
     */
    fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    /**
     * Método responsável por cadastrar o usuário
     *
     * @param  userRequest  DTO de User
     * @return O usuário criado
     */
    fun createUser(userRequest: UserRequest): User {
        val user = setUser(userRequest)
        validatePostFields(user)

        return userRepository.save(user)
    }

    /**
     * Método responsável por atualizar a senha de algum usuário
     *
     * @param  user  com a nova senha
     * @return Usuário com a senha alterada
     */
    fun updateUser(user: User): User {
        validatePassword(user)

        return userRepository.save(user)
    }

    /**
     * Método responsável por listar todos os usuários cadastrados
     *
     * @return Lista dos usuários cadastrados
     */
    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    /**
     * Método responsável por listar o usuário pelo Id
     *
     * @param  id  do usuário a ser encontrado
     * @return Optional do tipo User
     */
    fun getById(id: Long): Optional<User> {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw BudgetException(404, "Usuário não encontrado")
        }

        return user
    }

    /**
     * Método responsável por usuário pelo Id
     *
     * @param  id  do usuário a ser deletado
     */
    fun deleteById(id: Long) {
        val user = userRepository.findById(id)

        if (!user.isPresent) {
            throw BudgetException(404, "Usuário não encontrado")
        }

        userRepository.deleteById(id)
    }

    /**
     * Método responsável por criar um objeto User
     *
     * @param userRequest DTO que será usado para criar um User
     *
     * @return User criado
     */
    private fun setUser(userRequest: UserRequest): User {
        var user: User = User()
        user.cpf = userRequest.cpf
        user.name = userRequest.name
        user.password = userRequest.password
        user.email = userRequest.email
        user.income = userRequest.income

        return user
    }

    /**
     * Método responsável por validar a senha no POST e PUT
     *
     *  @param  user  User com a senha a ser validada
     */
    fun validatePassword(user: User) {
        if (user.password?.length!! < 8) {
            throw BudgetException(400, "Senha deve ser maior que 8 caracteres")
        }
    }

    /**
     * Método responsável por validar os campos no POST
     *
     *  @param  user  usuário a ser validado
     */
    fun validatePostFields(user: User) {
        var pattern: Pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")
        var matcher: Matcher = pattern.matcher(user.email)

        if (user.name?.isEmpty()!!) {
            throw BudgetException(400, "Nome obrigatório")
        }

        if (user.name?.length!! < 3 || user.name?.length!! > 80) {
            throw BudgetException(400, "Nome deve ter tamanho entre 3 e 80 caracteres")
        }

        if (user.cpf?.isEmpty()!! || user.cpf?.length != 11) {
            throw BudgetException(400, "CPF inválido")
        }

        if (user.email?.isEmpty()!!) {
            throw BudgetException(400, "Email obrigatório")
        }

        if (user.income == null) {
            throw BudgetException(400, "Renda inválida")
        }

        if (!matcher.matches()) {
            throw BudgetException(400, "Email inválido")
        }

        if (userRepository.existsByCpf(user.cpf!!)) {
            throw BudgetException(400, "CPF já cadastrado")
        }

        if (userRepository.existsByEmail(user.email!!)) {
            throw BudgetException(400, "Email já cadastrado")
        }

        validatePassword(user)
    }
}