package com.tinkoff.android_homework.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkoff.android_homework.domain.main.usecases.SubscribeDetailUseCase
import com.tinkoff.android_homework.presentation.mappers.detail.DetailToUiItemMapper
import com.tinkoff.android_homework.presentation.model.DetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author d.shtaynmets
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val subscribeDetailUseCase: SubscribeDetailUseCase,
    private val uiItemMapper: DetailToUiItemMapper,
) : ViewModel() {

    private val _details = MutableLiveData<DetailItem>()
    val details: LiveData<DetailItem> get() = _details

    fun gt(id: Int) {
        viewModelScope.launch {
            _details.value = uiItemMapper.invoke(subscribeDetailUseCase.getDetail(id))
        }
    }
}
