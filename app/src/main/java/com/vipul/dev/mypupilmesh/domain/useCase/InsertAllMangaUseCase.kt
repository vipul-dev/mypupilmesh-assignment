package com.vipul.dev.mypupilmesh.domain.useCase

import com.vipul.dev.mypupilmesh.data.local.entity.MangaEntity
import com.vipul.dev.mypupilmesh.domain.repository.MangaRepository
import javax.inject.Inject

class InsertAllMangaUseCase @Inject constructor(private val repository: MangaRepository) {

    suspend operator fun invoke(dataList: List<MangaEntity>){
        repository.insertAllMangaInDB(dataList)
    }
}