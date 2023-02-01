package com.example.wiremock_stages_compose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wiremock_stages_compose.domain.PullDataUseCase
import com.example.wiremock_stages_compose.domain.ResetScenarioUseCase
import com.example.wiremock_stages_compose.domain.model.Result
import com.example.wiremock_stages_compose.ui.mapper.toUi
import com.example.wiremock_stages_compose.ui.model.UiState
import com.example.wiremock_stages_compose.ui.model.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class StageViewModel(
    private val pullDataUseCase: PullDataUseCase,
    private val resetScenarioUseCase: ResetScenarioUseCase
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private var pullJob: Job = Job()

    fun pullData(paymentCode: String) {
        pullJob = Job()
        viewModelScope.launch(Dispatchers.IO + pullJob) {
            state = state.copy(uiStatus = UiStatus.LOADING)
            withContext(Dispatchers.IO) { TimeUnit.SECONDS.sleep(2L) }
            pullDataUseCase.execute(paymentCode).map {
                state = when (it) {
                    is Result.Success -> state.copy(
                        pullData = it.data.toUi(),
                        index = state.index + 1,
                        uiStatus = UiStatus.SUCCESS
                    )
                    is Result.Error -> state.copy(
                        uiStatus = UiStatus.ERROR,
                        error = it.toString()
                    )
                }
            }.stateIn(this)
        }
    }

    fun resetScenario() {
        viewModelScope.launch(Dispatchers.IO) {
            resetScenarioUseCase.execute().stateIn(this)
            pullJob.cancel()
            state = UiState()
        }
    }

}