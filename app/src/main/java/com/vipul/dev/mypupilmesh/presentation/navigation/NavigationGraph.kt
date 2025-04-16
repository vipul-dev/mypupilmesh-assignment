package com.vipul.dev.mypupilmesh.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthDest {

    @Serializable
    data object SplashScreen: AuthDest

    @Serializable
    data object SignInScreen : AuthDest

}

@Serializable
sealed interface MangaDest {

    @Serializable
    data object MangaGridListScreen : MangaDest

    @Serializable
    data object MangaDetailScreen : MangaDest

}

@Serializable
sealed interface FaceRecognitionDest{

    @Serializable
    data object FaceRecognitionScreen: FaceRecognitionDest
}

@Serializable
sealed interface DashboardDest{

    @Serializable
    data object DashboardScreen: DashboardDest
}