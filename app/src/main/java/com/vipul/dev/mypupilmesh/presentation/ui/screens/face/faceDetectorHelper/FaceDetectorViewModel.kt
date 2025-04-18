
package com.vipul.dev.mypupilmesh.presentation.ui.screens.face.faceDetectorHelper

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  This ViewModel is used to store face detector helper settings
 */

class FaceDetectorViewModel : ViewModel() {
    private var _delegate: Int = FaceDetectorHelper.DELEGATE_CPU
    private var _threshold: Float =
        FaceDetectorHelper.THRESHOLD_DEFAULT

    val currentDelegate: Int get() = _delegate
    val currentThreshold: Float get() = _threshold

    fun setDelegate(delegate: Int) {
        _delegate = delegate
    }

    fun setThreshold(threshold: Float) {
        _threshold = threshold
    }
}