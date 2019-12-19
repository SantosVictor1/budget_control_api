package com.budget.api.model

import org.hibernate.validator.constraints.br.CPF
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    var id: Long? = null

    @NotBlank(message = "Nome obrigatório")
    @Size(min = 3, max = 80, message = "Nome deve ter mínimo de 3 caracteres e máximo de 80 caracteres")
    @Column(name = "name")
    var name: String? = null

    @NotBlank(message = "CPF obrigatório")
    @CPF
    @Column(name = "cpf", unique = true)
    var cpf: String? = null

    @NotBlank(message = "Email obrigatório")
    @Email(message = "Email inválido")
    @Column(name = "email", unique = true)
    var email: String? = null

    @NotBlank(message = "Senha obrigatória")
    @Size(min = 8, message = "Senha deve ser maior que 8 caracteres")
    @Column(name = "password")
    var password: String? = null

    @NotNull(message = "Renda obrigatória")
    @Column(name = "income")
    var income: Double? = null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE])
    var spents: List<Spent>? = null
}