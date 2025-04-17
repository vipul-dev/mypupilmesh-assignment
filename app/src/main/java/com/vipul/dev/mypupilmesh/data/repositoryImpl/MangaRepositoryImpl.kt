package com.vipul.dev.mypupilmesh.data.repositoryImpl

import com.vipul.dev.mypupilmesh.data.local.AppDao
import com.vipul.dev.mypupilmesh.data.local.entity.MangaEntity
import com.vipul.dev.mypupilmesh.data.remote.MangaApiServices
import com.vipul.dev.mypupilmesh.data.remote.model.MangaData
import com.vipul.dev.mypupilmesh.domain.repository.MangaRepository
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaApi: MangaApiServices,
    private val appDao: AppDao
) : MangaRepository {
    override suspend fun getManga(page: Int): List<MangaData> {
        return mangaApi.getManga(page).data.map { it.toDomain() }
    }

    override suspend fun clearMangaData() {
        appDao.clearAll()
    }

    override suspend fun insertAllMangaInDB(dataList: List<MangaEntity>) {
        appDao.insertMangaAll(dataList)
    }

    override suspend fun getAllMangaFromDB(): List<MangaEntity> {
        return appDao.getAllMangaData()
    }


}