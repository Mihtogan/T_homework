package com.tinkoff.android_homework.presentation.mappers.detail

import com.tinkoff.android_homework.domain.main.entities.Detail
import com.tinkoff.android_homework.presentation.model.DetailItem
import javax.inject.Inject

class DetailToUiItemMapper @Inject constructor() : (Detail) -> DetailItem {

    override operator fun invoke(p1: Detail): DetailItem {
        return DetailItem(
            type = p1.type,
            amount = 0,
            comment = p1.comment,
            positions = p1.positions
        )
    }
}