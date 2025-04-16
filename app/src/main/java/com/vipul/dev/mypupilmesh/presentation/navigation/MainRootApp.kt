package com.vipul.dev.mypupilmesh.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vipul.dev.mypupilmesh.presentation.ui.screens.dashboard.DashboardScreen
import com.vipul.dev.mypupilmesh.presentation.ui.screens.signIn.SignInScreen
import com.vipul.dev.mypupilmesh.presentation.ui.screens.splashScreen.SplashScreen

@Composable
fun MainRootApp() {
    val navController = rememberNavController()


    NavHost(
        modifier = Modifier, navController = navController, startDestination = AuthDest.SplashScreen
    ) {

        composable<AuthDest.SplashScreen> {
            SplashScreen(navController)
        }

        composable<AuthDest.SignInScreen> {
            SignInScreen(navController)
        }

        composable<DashboardDest.DashboardScreen> {
            DashboardScreen()
        }

    }


}
