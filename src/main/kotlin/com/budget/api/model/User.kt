package com.budget.api.model

import com.budget.api.dto.request.UserRequestDTO
import com.budget.api.dto.response.success.UserResponseDTO
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * Created by Victor Santos on 24/11/2019
 */
@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "userId")
    var id: String? = UUID.randomUUID().toString(),

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

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE])
    var spent: MutableList<Spent>?
) {
    companion object {
        fun fromUserRequestToEntity(userRequestDTO: UserRequestDTO): User {
            return User(UUID.randomUUID().toString(), userRequestDTO.name, userRequestDTO.cpf, userRequestDTO.email, userRequestDTO.password, userRequestDTO.income, null)
        }

        fun fromUserResponseToEntity(userResponseDTO: UserResponseDTO): User {
            return User(userResponseDTO.id, userResponseDTO.name, userResponseDTO.cpf, userResponseDTO.email, "", userResponseDTO.income, userResponseDTO.spent)
        }
    }
}