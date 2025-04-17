package com.vipul.dev.mypupilmesh.networkObserver

import com.vipul.dev.mypupilmesh.presentation.utils.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface NetworkObserver {

    fun observe(): Flow<NetworkStatus>
}