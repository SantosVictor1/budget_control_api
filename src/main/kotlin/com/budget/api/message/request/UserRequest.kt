package com.budget.api.message.request

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Victor Santos on 06/12/2019
 */
class UserRequest {
    @NotNull(message = "Nome obrigatório")
    @Size(min = 3, max = 80, message = "Nome deve ter entre 3 e 80 caracteres")
    val name: String? = null

    @NotNull(message = "Email obrigatório")
    @Email(message = "Email inválido")
    val email: String? = null

    @NotNull(message = "CPF obrigatório")
    @CPF(message = "CPF inválido")
    val cpf: String? = null

    @NotNull(message = "Senha obrigatória")
    @Size(min = 8, message = "Senha deve ser maior que 8 caracteres")
    val password: String? = null

    @NotNull(message = "Renda obrigatória")
    val income: Double? = null
}