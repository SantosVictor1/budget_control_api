package com.budget.api.message.request

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Victor Santos on 06/12/2019
 */
class UserRequest(
    @NotBlank(message = "Nome obrigatório")
    @Size(min = 3, max = 80, message = "Nome deve ter mínimo de 3 caracteres e máximo de 80 caracteres")
    val name: String,

    @NotBlank(message = "Email obrigatório")
    @Email(message = "Email inválido")
    val email: String,

    @NotBlank(message = "CPF obrigatório")
    @CPF
    val cpf: String,

    @NotBlank(message = "Senha obrigatória")
    @Size(min = 8, message = "Senha deve ser maior que 8 caracteres")
    val password: String,

    @NotNull(message = "Renda obrigatória")
    var income: Double
)