package com.budget.api.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "spent")
class Spent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spentId")
    var id: Long? = null

    @NotNull(message = "Valor obrigatório")
    @Column(name = "value")
    var spentValue: Double? = null

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "spentDate")
    var spentDate: Date? = null

    @NotBlank(message = "Local obrigatório")
    @Column(name = "description")
    var descritpion: String? = null

    @ManyToOne(targetEntity = User::class)
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    var user: User? = null
}