package com.houston.docornot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houston.docornot.domain.api.Interactor
import com.houston.docornot.domain.model.Action
import com.houston.docornot.domain.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val interactor: Interactor
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Idle)
    val state: StateFlow<State> = _state

    fun dispatch(action: Action) {
        when (action) {
            is Action.Login -> {
                _state.update { State.Loading }
                login(action.portal, action.email, action.password)
            }
        }
    }

    private fun login(portal: String, email: String, password: String) {
        viewModelScope.launch {
            interactor.login(portal, email, password).collect { pair ->
                if (pair.first != null) _state.update { State.Success(token = pair.first!!) }
                if (pair.second != null) _state.update { State.Error(message = pair.second!!) }
            }
        }
    }
}