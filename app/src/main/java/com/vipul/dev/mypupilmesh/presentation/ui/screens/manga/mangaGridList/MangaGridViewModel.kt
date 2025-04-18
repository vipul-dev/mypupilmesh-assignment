package com.vipul.dev.mypupilmesh.presentation.ui.screens.manga.mangaGridList

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.dev.mypupilmesh.data.remote.model.MangaData
import com.vipul.dev.mypupilmesh.domain.useCase.ClearMangaDataUSeCase
import com.vipul.dev.mypupilmesh.domain.useCase.FetchMangaApiUseCase
import com.vipul.dev.mypupilmesh.domain.useCase.GetMangaFromDBUseCase
import com.vipul.dev.mypupilmesh.domain.useCase.InsertAllMangaUseCase
import com.vipul.dev.mypupilmesh.networkObserver.NetworkObserverImpl
import com.vipul.dev.mypupilmesh.presentation.utils.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MangaGridViewModel @Inject constructor(
    private val mangaApiUseCase: FetchMangaApiUseCase,
    private val clearMangaDataUSeCase: ClearMangaDataUSeCase,
    private val insertAllMangaUseCase: InsertAllMangaUseCase,
    private val getMangaFromDBUseCase: GetMangaFromDBUseCase,
    private val networkObserver: NetworkObserverImpl
) : ViewModel() {


    var mangaList = mutableStateListOf<MangaData>()
        private set

    var error = mutableStateOf("")
        private set

    private val _isConnected = MutableStateFlow(true)
    val isConnected: StateFlow<Boolean> = _isConnected


    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    private var currentPage = 1
    var isPaging = false


    init {
        observeNetwork()
        getManga()
    }

    fun observeNetwork() {
        viewModelScope.launch {
            networkObserver.observe().collect { status ->
                _isConnected.value = (status == NetworkStatus.Available)

                if (status == NetworkStatus.Available) {
                    currentPage = 1
                    getManga()
                }
            }
        }
    }

    fun getManga() {
        viewModelScope.launch {
            try {
                if (isConnected.value) {
                    if (currentPage == 1) {
                        _isLoading.value = true
                    } else {
                        isPaging = true
                    }
                    mangaList.addAll(mangaApiUseCase.invoke(currentPage))
                    if (currentPage == 1) clearMangaDataUSeCase.invoke()

                    insertAllMangaUseCase.invoke(mangaList.map { it.toEntity() })



                    currentPage++
                } else {
                    val roomData = getMangaFromDBUseCase.invoke()
                    mangaList.addAll(roomData.map { it.toDomain() })
                }
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is HttpException) {
                    val code = e.code()
                    val errorBody = e.response()?.errorBody()?.string()
                    val url = e.response()?.raw()?.request?.url.toString()

                    Log.e("HTTP_ERROR", "Code: $code")
                    Log.e("HTTP_ERROR", "Error Body: $errorBody")
                    Log.e("HTTP_ERROR", "URL: $url")

                    _isError.value = true
                    // Optionally, show a user-friendly message
                    when (code) {
                        429 -> {
                            // Too many requests - show rate limit message
                            error.value =
                                "You have exceeded the DAILY quota for Requests on your current plan, BASIC."
                        }

                        else -> {
                            error.value = "Something went wrong: $code"
                        }
                    }
                } else {
                    e.printStackTrace()
                }
            } finally {
                _isLoading.value = false
                isPaging = false
            }
        }
    }

}