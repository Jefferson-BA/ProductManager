package com.tecsup.productmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.productmanager.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthState(
    val loading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class AuthViewModel(
    val repo: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> get() = _state

    fun currentUserId(): String? = repo.currentUserId()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthState(loading = true)

            val result = repo.login(email, password)

            if (result.isSuccess) {
                _state.value = AuthState(success = true)
            } else {
                _state.value = AuthState(error = result.exceptionOrNull()?.message)
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthState(loading = true)

            val result = repo.register(email, password)

            if (result.isSuccess) {
                _state.value = AuthState(success = true)
            } else {
                _state.value = AuthState(error = result.exceptionOrNull()?.message)
            }
        }
    }
}
