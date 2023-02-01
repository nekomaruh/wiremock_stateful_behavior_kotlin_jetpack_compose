package com.example.wiremock_stages_compose.ui.model

enum class UiStatus { INIT, LOADING, ERROR, SUCCESS }

data class UiState(
    val pullData: PullData? = null,
    val uiStatus: UiStatus = UiStatus.INIT,
    val error: String? = null,
    val index: Int = 0,
)