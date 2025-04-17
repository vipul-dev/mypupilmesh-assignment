package com.vipul.dev.mypupilmesh.domain.useCase

import com.vipul.dev.mypupilmesh.domain.repository.MangaRepository
import javax.inject.Inject

class ClearMangaDataUSeCase @Inject constructor(private val repository: MangaRepository) {
    suspend operator fun invoke() {
        repository.clearMangaData()
    }
}