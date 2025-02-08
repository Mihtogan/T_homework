package com.tinkoff.android_homework.domain.main.entities

/**
 * @author d.shtaynmets
 */
data class Operation(
    val id: Long,
    val type: OperationType,
    val name: String,
    val amount: Int,
)
