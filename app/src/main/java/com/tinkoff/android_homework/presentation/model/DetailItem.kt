package com.tinkoff.android_homework.presentation.model

import com.tinkoff.android_homework.domain.main.entities.OperationType

/**
 * @author d.shtaynmets
 */
data class DetailItem (
    val type: OperationType,
    val amount: Int,
    val comment: String,
    val positions: String
)
