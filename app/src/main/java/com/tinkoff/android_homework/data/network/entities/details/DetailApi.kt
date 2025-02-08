package com.tinkoff.android_homework.data.network.entities.details

import kotlinx.serialization.Serializable

/**
 * @author d.shtaynmets
 */
@Serializable
data class DetailApi(
    val amount: Int,
    val comment: String,
    val positions: List<String>
)
