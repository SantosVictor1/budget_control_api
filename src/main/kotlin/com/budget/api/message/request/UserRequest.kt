package com.budget.api.message.request

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class UserRequest(
    @NotBlank(message = "Nome obrigatório")
    @Size(min = 3, max = 80, message = "Nome deve ter mínimo de 3 caracteres e máximo de 80 caracteres")
    val name: String,

    @NotBlank
    @Email(message = "Email inválido")
    val email: String,

    @NotBlank
    @CPF
    val cpf: String,

    @NotBlank
    @Size(min = 8, message = "Senha deve ser maior que 8 caracteres")
    val password: String
)