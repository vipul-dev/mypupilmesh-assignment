package com.vipul.dev.mypupilmesh.presentation.ui.screens.signIn

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,
    val error: String? = null,
    val showUserExistDialog: Boolean = false
)
