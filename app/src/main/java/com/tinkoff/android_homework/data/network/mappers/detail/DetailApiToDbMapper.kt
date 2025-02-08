package com.tinkoff.android_homework.data.network.mappers.detail

import com.tinkoff.android_homework.data.network.entities.details.DetailApi
import com.tinkoff.android_homework.data.storage.entities.DetailDb
import com.tinkoff.android_homework.domain.main.entities.OperationType
import javax.inject.Inject

/**
 * @author d.shtaynmets
 */
class DetailApiToDbMapper @Inject constructor() : (DetailApi, Long, OperationType) -> DetailDb {

    override fun invoke(
        detail: DetailApi,
        id: Long,
        opTyp: OperationType
    ): DetailDb {
        return DetailDb(
            id = id,
            type = opTyp,
            amount = detail.amount,
            comment = detail.comment,
            positions = detail.positions
        )
    }

}
