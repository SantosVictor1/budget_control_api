package com.budget.api.model

import com.budget.api.dto.request.SpentRequestDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * Created by Victor Santos on 16/12/2019
 */
@Entity
@Table(name = "spent")
class Spent(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "spentId")
    var id: String? = UUID.randomUUID().toString(),

    @Column(name = "value", nullable = false)
    var spentValue: Double,

    @Column(name = "spentDate", nullable = false)
    var spentDate: Date,

    @Column(name = "description", nullable = false)
    var description: String,

    @JsonBackReference
    @ManyToOne(targetEntity = User::class)
    @JoinColumn(name = "userId", nullable = false)
    var user: User? = null
) {
    companion object {
        fun toEntity(spentRequestDTO: SpentRequestDTO, user: User): Spent {
            return Spent(UUID.randomUUID().toString(), spentRequestDTO.spentValue, spentRequestDTO.spentDate!!, spentRequestDTO.description, user)
        }
    }
}