package com.budget.api.model

import java.util.*
import javax.persistence.*

/**
 * Created by Victor Santos on 16/12/2019
 */
@Entity
@Table(name = "spent")
data class Spent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spentId")
    val id: Long,

    @Column(name = "value", nullable = false)
    val spentValue: Double,

    @Column(name = "spentDate", nullable = false)
    val spentDate: Date = Date(),

    @Column(name = "description",  nullable = false)
    val description: String,

    @ManyToOne(targetEntity = User::class)
    @JoinColumn(name = "userId", nullable = false)
    val user: User
)