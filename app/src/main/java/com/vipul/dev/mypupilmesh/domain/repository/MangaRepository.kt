package com.vipul.dev.mypupilmesh.domain.repository

import com.vipul.dev.mypupilmesh.data.local.entity.MangaEntity
import com.vipul.dev.mypupilmesh.data.remote.model.MangaData
import com.vipul.dev.mypupilmesh.data.remote.model.MangaResponse

interface MangaRepository {
    suspend fun getManga(page:Int): List<MangaData>
    suspend fun clearMangaData()
    suspend fun insertAllMangaInDB(dataList:List<MangaEntity>)
    suspend fun getAllMangaFromDB(): List<MangaEntity>
}