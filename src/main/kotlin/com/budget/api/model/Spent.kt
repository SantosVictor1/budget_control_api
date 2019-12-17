package com.budget.api.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
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

//    @NotEmpty(message = "Data obrigatória")
//    @JsonFormat(pattern = "dd/MM/yyyy")
//    @Column(name = "spentDate")
//    var spentDate: Date? = null

    @NotBlank(message = "Local obrigatório")
    @Column(name = "spentPlace")
    var spentPlace: String? = null

    @ManyToOne(targetEntity = User::class)
    @JoinColumn(name = "userId", nullable = false)
    var user: User? = null
}