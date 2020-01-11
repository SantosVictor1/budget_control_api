package com.budget.api.model

import com.budget.api.dto.request.UserRequestDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

/**
 * Created by Victor Santos on 24/11/2019
 */
@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "cpf", unique = true, nullable = false)
    var cpf: String,

    @Column(name = "email", unique = true, nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "income", nullable = false)
    var income: Double,

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE])
    var spent: MutableList<Spent>?
) {
    companion object {
        fun toEntity(userRequestDTO: UserRequestDTO): User {
            return User(null, userRequestDTO.name, userRequestDTO.cpf, userRequestDTO.email, userRequestDTO.password, userRequestDTO.income, null)
        }
    }
}