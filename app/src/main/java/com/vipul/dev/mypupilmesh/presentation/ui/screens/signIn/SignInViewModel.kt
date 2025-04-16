package com.vipul.dev.mypupilmesh.presentation.ui.screens.signIn

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.dev.mypupilmesh.domain.useCase.CheckUserExistsUseCase
import com.vipul.dev.mypupilmesh.domain.useCase.SaveUserUseCase
import com.vipul.dev.mypupilmesh.presentation.utils.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val checkUserExistsUseCase: CheckUserExistsUseCase,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {


    private val _isLogin = MutableStateFlow<Boolean?>(null)
    val isLogin = _isLogin.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreManager.isLoggedIn.collect {
                _isLogin.value = it
            }
        }
    }


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

        if (!Patterns.EMAIL_ADDRESS.matcher(uiState.email).matches()) {
            uiState = uiState.copy(error = "Please enter valid email")
            return
        }

        if (uiState.password.length < 6) {
            uiState = uiState.copy(error = "Password should be at least 6 characters")
            return
        }

        uiState = uiState.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {

                if (checkUserExistsUseCase.invoke(uiState.email)) {
                    uiState = uiState.copy(isLoading = false, showUserExistDialog = true)
                    dataStoreManager.setLoginState(true)
                } else {
                    saveUserUseCase.invoke(uiState.email, uiState.password)
                    dataStoreManager.setLoginState(true)
                    delay(1000)
                    uiState = uiState.copy(isLoading = false, isSignedIn = true)
                }
            } catch (e: Exception) {
                uiState = uiState.copy(isLoading = false, error = e.localizedMessage)
            }

        }
    }

    fun resetError() {
        uiState = uiState.copy(error = null)
    }

    fun dismissUserExistDialog() {
        uiState = uiState.copy(showUserExistDialog = false)
    }

    fun setLogOut() {
        viewModelScope.launch {
            dataStoreManager.setLoginState(false)
        }
    }


}