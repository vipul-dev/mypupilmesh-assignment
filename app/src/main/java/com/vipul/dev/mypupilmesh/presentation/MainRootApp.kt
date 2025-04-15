package com.vipul.dev.mypupilmesh.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vipul.dev.mypupilmesh.presentation.navigation.AuthDest
import com.vipul.dev.mypupilmesh.presentation.ui.screens.signIn.SignInScreen

@Composable
fun MainRootApp(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()

        NavHost(modifier = Modifier.padding(innerPadding), navController = navController, startDestination = AuthDest.SignInScreen){

            composable<AuthDest.SignInScreen>{
                SignInScreen(navController)
            }

        }
    }
}