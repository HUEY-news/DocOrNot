package com.houston.docornot.domain.model

sealed class State {
    object Idle : State()
    object Loading : State()
    data class Success(val token: String) : State()
    data class Error(val message: String) : State()
}