package com.vipul.dev.mypupilmesh.domain.useCase

import com.vipul.dev.mypupilmesh.data.remote.model.MangaData
import com.vipul.dev.mypupilmesh.data.remote.model.MangaResponse
import com.vipul.dev.mypupilmesh.domain.repository.MangaRepository
import javax.inject.Inject

class FetchMangaApiUseCase @Inject constructor(private val repository: MangaRepository) {

    suspend operator fun invoke(page:Int): List<MangaData>{
       return repository.getManga(page)
    }

}