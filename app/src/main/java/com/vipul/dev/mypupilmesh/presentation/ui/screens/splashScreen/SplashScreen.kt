package com.vipul.dev.mypupilmesh.presentation.ui.screens.splashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vipul.dev.mypupilmesh.presentation.navigation.AuthDest
import com.vipul.dev.mypupilmesh.presentation.navigation.DashboardDest
import com.vipul.dev.mypupilmesh.presentation.ui.screens.signIn.SignInViewModel

@Composable
fun SplashScreen(navController: NavController?, viewModel: SignInViewModel = hiltViewModel()) {


    val isLoggedIn = viewModel.isLogin.collectAsState()


    LaunchedEffect(isLoggedIn.value) {
        if (isLoggedIn.value != null) {

           navController?.navigate( if (isLoggedIn.value == true) DashboardDest.DashboardScreen else AuthDest.SignInScreen){
                popUpTo(AuthDest.SplashScreen){
                    inclusive=true
                }
            }

        }
    }

    Box(modifier= Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center){
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSplashScreen(){
    SplashScreen(null)
}