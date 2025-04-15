package com.vipul.dev.mypupilmesh.presentation.navigation


sealed interface AuthDest{

//    @Serializable
    data object SignInScreen: AuthDest

}

sealed interface MangaDest{

}

sealed interface FaceRecognitionDest{

}