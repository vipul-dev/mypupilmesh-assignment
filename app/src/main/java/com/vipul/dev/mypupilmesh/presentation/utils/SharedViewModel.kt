package com.vipul.dev.mypupilmesh.presentation.utils

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vipul.dev.mypupilmesh.data.remote.model.MangaData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {

    private val _selectedMangaData = mutableStateOf<MangaData?>(null)
    val selectedMangaData: State<MangaData?> = _selectedMangaData

    fun setSelectedMangaData(manga: MangaData) {
        _selectedMangaData.value = manga
    }

}