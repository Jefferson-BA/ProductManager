package com.tecsup.productmanager.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.tecsup.productmanager.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    if (state.success) {
        onSuccess()
    }
}
