package com.tinkoff.android_homework.domain.main.entities

/**
 * @author d.shtaynmets
 */
data class Detail(
    val type: OperationType,
    val amount: Int,
    val comment: String,
    val positions: List<String>,
)
