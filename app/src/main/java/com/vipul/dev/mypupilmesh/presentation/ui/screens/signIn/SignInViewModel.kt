package com.vipul.dev.mypupilmesh.presentation.ui.screens.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.dev.mypupilmesh.domain.useCase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {


    var uiState by mutableStateOf(SignInUiState())
        private set


    fun onEmailChange(newEmail: String) {
        uiState = uiState.copy(email = newEmail)
    }

    fun onPasswordChange(newPwd: String) {
        uiState = uiState.copy(password = newPwd)
    }

    fun signIn() {
        if (uiState.email.isBlank() || uiState.password.isBlank()) {
            uiState = uiState.copy(error = "Please fill all fields")
            return
        }

        uiState = uiState.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {
                saveUserUseCase.invoke(uiState.email,uiState.password)
                delay(1000)
                uiState = uiState.copy(isLoading = false, isSignedIn = true)
            }catch (e: Exception){
                uiState = uiState.copy(isLoading = false, error = e.localizedMessage)
            }

        }
    }

    fun resetError() {
        uiState = uiState.copy(error = null)
    }


}