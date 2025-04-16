package com.vipul.dev.mypupilmesh.presentation.ui.screens.face

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun FaceRecognitionScreen(modifier: Modifier = Modifier) {


    Box(modifier= Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text("Face Recognition", fontSize = 24.sp)
    }

}