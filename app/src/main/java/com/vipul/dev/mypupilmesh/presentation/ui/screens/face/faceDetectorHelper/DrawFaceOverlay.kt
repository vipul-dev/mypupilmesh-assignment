package com.vipul.dev.mypupilmesh.presentation.ui.screens.face.faceDetectorHelper

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.mediapipe.tasks.vision.facedetector.FaceDetectorResult

@Composable
fun DrawFaceOverlay(
    detectionResult: FaceDetectorResult?,
    imageWidth: Int,
    imageHeight: Int,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = modifier) {
        val detections = detectionResult?.detections()
        val verticalOffset = 10.dp.toPx()

        if (detections.isNullOrEmpty()) {
            // No face detected — show red box across canvas
            val boxSize = 100.dp.toPx()
            val centerX = size.width / 2
            val centerY = size.height / 2

            val topLeft = Offset(centerX - boxSize / 2, centerY - boxSize / 2)

            drawRect(
                color = Color.Red,
                topLeft = topLeft,
                size = Size(boxSize, boxSize),
                style = Stroke(width = 4.dp.toPx())
            )
        } else {
            detectionResult?.let { result ->
                val scaleX = size.width / imageWidth.toFloat()
                val scaleY = size.height / imageHeight.toFloat()
                val scale = minOf(scaleX, scaleY)

                result.detections().forEach { detection ->
                    val category = detection.categories().firstOrNull()
                    val label = category?.categoryName() ?: "Unknown"
                    val score = category?.score() ?: 0f
                    val text = "$label ${String.format("%.2f", score)}"

                    val box = detection.boundingBox()
                    val left = box.left * scale
                    val top = box.top * scale
                    val right = box.right * scale
                    val bottom = box.bottom * scale

                    // Adjust the 'top' position to move the overlay down slightly
                    val verticalOffset = 50.dp.toPx() // Adjust this value as needed
                    val adjustedTop = top + verticalOffset

                    // Set the bounding box color based on face detection


                    // Draw bounding box
                    drawRect(
                        color = Color.Green,
                        topLeft = Offset(left, adjustedTop),
                        size = Size(right - left, bottom - top),
                        style = Stroke(width = 4.dp.toPx())
                    )

                    // Draw background for text
                    val padding = 8.dp.toPx()
                    val textLayout = textMeasurer.measure(AnnotatedString(text))
                    val textWidth = textLayout.size.width
                    val textHeight = textLayout.size.height
                    val boxWidth = textWidth + padding
                    val boxHeight = textHeight + padding


                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(left, adjustedTop),
                        size = Size(
                            textLayout.size.width + padding,
                            textLayout.size.height + padding
                        )
                    )

                    // Calculate offset to center text
                    val textX = left + (boxWidth - textWidth) / 2
                    val textY = adjustedTop + (boxHeight - textHeight) / 2

                    // Draw label
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            textMeasurer = textMeasurer,
                            text = AnnotatedString(text),
                            topLeft = Offset(textX, textY),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }
        }
    }
}