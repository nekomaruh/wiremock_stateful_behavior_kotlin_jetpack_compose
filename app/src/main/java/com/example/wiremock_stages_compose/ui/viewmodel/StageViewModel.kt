package com.example.wiremock_stages_compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wiremock_stages_compose.data.model.Result
import com.example.wiremock_stages_compose.domain.PullUseCase
import com.example.wiremock_stages_compose.domain.model.PullResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StageViewModel(
    private val pullUseCase: PullUseCase,
) : ViewModel() {

    private val _pullFlow: MutableStateFlow<Result<PullResponse>> =
        MutableStateFlow(Result.OnLoading())

    val pullFlow = _pullFlow.asStateFlow()

    fun getStatus(paymentCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pullUseCase.execute(paymentCode)
                .map { _pullFlow.emit(it) }
                .stateIn(this)
        }
    }
}