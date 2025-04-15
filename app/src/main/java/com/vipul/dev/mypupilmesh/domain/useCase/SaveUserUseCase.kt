package com.vipul.dev.mypupilmesh.domain.useCase

import com.vipul.dev.mypupilmesh.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(email: String,password: String){
        repository.saveUser(email,password)
    }
}