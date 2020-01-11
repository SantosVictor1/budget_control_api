package com.budget.api.model

import com.budget.api.dto.request.SpentRequestDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.util.*
import javax.persistence.*

/**
 * Created by Victor Santos on 16/12/2019
 */
@Entity
@Table(name = "spent")
class Spent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spentId")
    var id: Long? = null,

    @Column(name = "value", nullable = false)
    var spentValue: Double,

    @Column(name = "spentDate", nullable = false)
    var spentDate: Date,

    @Column(name = "description",  nullable = false)
    var description: String,

    @JsonBackReference
    @ManyToOne(targetEntity = User::class)
    @JoinColumn(name = "userId", nullable = false)
    var user: User? = null
) {
    companion object {
        fun toEntity(spentRequestDTO: SpentRequestDTO, user: User): Spent {
            return Spent(null, spentRequestDTO.spentValue, spentRequestDTO.spentDate!!, spentRequestDTO.description, user)
        }
    }
}