package com.vipul.dev.mypupilmesh.data.remote

import com.vipul.dev.mypupilmesh.data.remote.model.MangaData
import com.vipul.dev.mypupilmesh.data.remote.model.MangaResponse
import com.vipul.dev.mypupilmesh.presentation.utils.ConstantUtils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Header

interface MangaApiServices {

    @GET("manga/fetch")
    suspend fun getManga(
        @retrofit2.http.Query("page") page: Int,
        @retrofit2.http.Query("limit") limit: Int = 10,
        @Header("x-rapidapi-key") apiKey: String = API_KEY

    ): MangaResponse

}