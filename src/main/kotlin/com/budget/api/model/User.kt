package com.budget.api.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    var id: Long? = null;

    @NotBlank
    @Column(name = "name")
    var name: String? = null;

    @NotBlank
    @Column(name = "cpf", unique = true)
    var cpf: String? = null;

    @NotBlank
    @Column(name = "email", unique = true)
    var email: String? = null;

    @NotBlank
    @Column(name = "password")
    @Size(min = 8)
    var password: String? = null;
}