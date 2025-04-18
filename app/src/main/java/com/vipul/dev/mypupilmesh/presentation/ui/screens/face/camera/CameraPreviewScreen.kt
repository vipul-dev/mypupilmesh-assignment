package com.vipul.dev.mypupilmesh.presentation.ui.screens.face.camera

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.facedetector.FaceDetectorResult
import com.vipul.dev.mypupilmesh.presentation.ui.screens.face.faceDetectorHelper.DrawFaceOverlay
import com.vipul.dev.mypupilmesh.presentation.ui.screens.face.faceDetectorHelper.FaceDetectorHelper
import java.util.concurrent.Executors

@Composable
fun CameraPreviewScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val detectionResultState = remember { mutableStateOf<FaceDetectorResult?>(null) }
    val imageWidthState = remember { mutableStateOf(0) }
    val imageHeightState = remember { mutableStateOf(0) }

    AndroidView(factory = { ctx ->
        PreviewView(ctx).apply {
            scaleType = PreviewView.ScaleType.FILL_CENTER
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }, modifier = Modifier.fillMaxSize(), update = { preview ->

        startCamera(
            preview,
            context,
            lifecycleOwner,
            onResult = { detectionResult, imageWidth, imageHeight ->
                detectionResultState.value = detectionResult
                imageWidthState.value = imageWidth
                imageHeightState.value = imageHeight
            })
    })

    if (detectionResultState.value != null) {
        DrawFaceOverlay(
            detectionResult = detectionResultState.value,
            imageWidth = imageWidthState.value,
            imageHeight = imageHeightState.value,
            modifier = Modifier.fillMaxSize()
        )
    }

}

fun startCamera(
    previewView: PreviewView,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    onResult: (FaceDetectorResult, Int, Int) -> Unit
) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()

        val preview = Preview.Builder().build().also {
            it.surfaceProvider = previewView.surfaceProvider
        }

        // Image analyzer with mediapipe face detection apo

        val imageAnalyzer = ImageAnalysis.Builder()
            .setTargetRotation(previewView.display.rotation)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888).build()

        val faceDetectorHelper = FaceDetectorHelper(
            threshold = 0.5f,
            currentDelegate = FaceDetectorHelper.DELEGATE_CPU,
            runningMode = RunningMode.LIVE_STREAM,
            context = context,
            faceDetectorListener = object : FaceDetectorHelper.DetectorListener {
                override fun onError(error: String, errorCode: Int) {
                    Log.e("FaceDetection", "Error: $error")
                }

                override fun onResults(resultBundle: FaceDetectorHelper.ResultBundle) {
                    Log.d("FaceDetection", "Detected ${resultBundle.results[0].detections().size} faces")

                    val detectionResult = resultBundle.results[0]

                    onResult(
                        detectionResult,
                        resultBundle.inputImageWidth,
                        resultBundle.inputImageHeight
                    )

                }

            })

        imageAnalyzer.setAnalyzer(
            Executors.newSingleThreadExecutor(), faceDetectorHelper::detectLivestreamFrame
        )

        val cameraSelector =
            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT).build()

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner, cameraSelector, preview, imageAnalyzer
            )
        } catch (e: Exception) {
            e.printStackTrace()
//            Log.e("CameraX", "Use case binding failed", exc)
        }

    }, ContextCompat.getMainExecutor(context))

}