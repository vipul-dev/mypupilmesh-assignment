package com.vipul.dev.mypupilmesh.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface AuthDest{

    @Serializable
    data object SignInScreen: AuthDest

}

sealed interface MangaDest{

}

sealed interface FaceRecognitionDest{

}