package com.tinkoff.android_homework.presentation.model.operations

import com.tinkoff.android_homework.domain.main.entities.OperationType

/**
 * @author d.a.korotkov
 */
data class OperationItem(
    val id: Long,
    val operationType: OperationType,
    val operationTitle: String,
    val operationSum: Int
)
