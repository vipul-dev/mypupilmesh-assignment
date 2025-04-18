package com.vipul.dev.mypupilmesh.presentation.ui.screens.face

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vipul.dev.mypupilmesh.presentation.ui.screens.face.camera.CameraPermissionWrapper
import com.vipul.dev.mypupilmesh.presentation.ui.screens.face.camera.CameraPreviewScreen

@Composable
fun FaceRecognitionScreen(modifier: Modifier = Modifier) {
    CameraPermissionWrapper {
        CameraPreviewScreen()
    }
}