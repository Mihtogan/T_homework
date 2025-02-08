package com.tinkoff.android_homework.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkoff.android_homework.domain.main.usecases.SubscribeDetailUseCase
import com.tinkoff.android_homework.presentation.mappers.detail.DetailToUiItemMapper
import com.tinkoff.android_homework.presentation.model.DetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author d.shtaynmets
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val subscribeDetailUseCase: SubscribeDetailUseCase,
    val uiItemMapper: DetailToUiItemMapper,
) : ViewModel() {

    private val _details: MutableStateFlow<DetailItem?> = MutableStateFlow(null)
    val details: StateFlow<DetailItem?> = _details.asStateFlow()

    fun gt(id: Int) {
        viewModelScope.launch {

            _details.value = uiItemMapper.invoke(subscribeDetailUseCase.getDetail(id))
        }
    }
}
