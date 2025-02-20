package com.houston.docornot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houston.docornot.domain.model.Action
import com.houston.docornot.domain.api.Repository
import com.houston.docornot.domain.model.State
import com.houston.docornot.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val bufferSize = 8
    private val actions = MutableSharedFlow<Action>(extraBufferCapacity = bufferSize)

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        store()
    }

    private fun store() {
        viewModelScope.launch {
            withContext(dispatcher) {
                actions.collect { action ->
                    when (action) {
                        is Action.Login -> {
                            _state.update { loginState ->
                                loginState.copy(
                                    isLoading = true,
                                    isSuccess = false,
                                    isError = false,
                                    errorMessage = null
                                )
                            }
                            val result = repository.login(
                                portal = action.portal,
                                email = action.email,
                                password = action.password
                            )
                            reduce(result)
                        }
                    }
                }
            }
        }
    }

    private fun reduce(result: Resource<Any>) {
        when (result) {
            is Resource.Success -> {
                _state.update { loginState ->
                    loginState.copy(
                        isLoading = false,
                        isSuccess = true,
                        isError = false,
                        errorMessage = null
                    )
                }
            }

            is Resource.Error -> {
                _state.update { loginState ->
                    loginState.copy(
                        isLoading = false,
                        isSuccess = false,
                        isError = true,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun dispatch(action: Action) {
        val success = actions.tryEmit(action)
        if (!success) error("БУФЕР ДЕЙСТВИЙ ПЕРЕПОЛНЕН")
    }
}
