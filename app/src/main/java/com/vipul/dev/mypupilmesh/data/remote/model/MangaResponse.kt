package com.vipul.dev.mypupilmesh.data.remote.model

import com.google.gson.annotations.SerializedName

data class MangaResponse(
    @SerializedName("code")
    val code:Int?=null,
    @SerializedName("data")
    val data: ArrayList<MangaData> = arrayListOf()
)