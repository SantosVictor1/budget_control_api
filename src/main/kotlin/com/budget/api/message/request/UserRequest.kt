package com.budget.api.message.request

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Victor Santos on 06/12/2019
 */
class UserRequest (
    private val userName: String? = null,
    private val userEmail: String? = null,
    private val userCpf: String? = null,
    private val userPassword: String? = null,
    private val userIncome: Double? = null
) {
    @NotNull(message = "Nome obrigatório")
    @Size(min = 3, max = 80, message = "Nome deve ter entre 3 e 80 caracteres")
    var name: String? = userName

    @NotNull(message = "Email obrigatório")
    @Email(message = "Email inválido")
    var email: String? = userEmail

    @NotNull(message = "CPF obrigatório")
    @CPF(message = "CPF inválido")
    var cpf: String? = userCpf

    @NotNull(message = "Senha obrigatória")
    @Size(min = 8, message = "Senha deve ser maior que 8 caracteres")
    var password: String? = userPassword

    @NotNull(message = "Renda obrigatória")
    var income: Double? = userIncome
}