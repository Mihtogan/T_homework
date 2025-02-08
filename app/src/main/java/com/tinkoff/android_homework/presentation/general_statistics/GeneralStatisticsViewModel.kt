package com.tinkoff.android_homework.presentation.general_statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkoff.android_homework.domain.main.usecases.SubscribeOperationsUseCase
import com.tinkoff.android_homework.domain.main.usecases.SubscribeTotalUseCase
import com.tinkoff.android_homework.presentation.mappers.operations.OperationToUiItemMapper
import com.tinkoff.android_homework.presentation.model.operations.OperationItem
import com.tinkoff.android_homework.domain.main.entities.OperationType
import com.tinkoff.android_homework.presentation.model.total.TotalItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralStatisticsViewModel @Inject constructor(
    private val subscribeTotalUseCase: SubscribeTotalUseCase,
    private val subscribeOperationsUseCase: SubscribeOperationsUseCase,
    private val uiMapper: OperationToUiItemMapper,
) : ViewModel() {

    private val _operations = MutableLiveData<List<OperationItem>>()
    val operations: LiveData<List<OperationItem>> get() = _operations

    private val _total = MutableLiveData<TotalItem?>()
    val total: LiveData<TotalItem?> get() = _total

    init {
        viewModelScope.launch {
            _operations.value =
                subscribeOperationsUseCase
                    .invoke()
                    .operations
                    .map { uiMapper.invoke(it) }

            _total.value = subscribeTotalUseCase
                .invoke()
                .map { total ->
                    val incomes = _operations
                        .value!!
                        .filter { it.operationType == OperationType.INCOME }
                        .sumOf { it.operationSum }

                    val outcomes = _operations
                        .value!!
                        .filter { it.operationType == OperationType.OUTCOME }
                        .sumOf { it.operationSum }

                    val progress = (outcomes.toFloat() / incomes.toFloat()) * 100f

                    TotalItem(
                        total = total.amount,
                        income = incomes,
                        outcome = outcomes,
                        progress = progress
                    )
                }.first()
        }
    }
}