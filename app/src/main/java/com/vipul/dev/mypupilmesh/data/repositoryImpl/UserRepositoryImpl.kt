package com.vipul.dev.mypupilmesh.data.repositoryImpl

import com.vipul.dev.mypupilmesh.data.local.AppDao
import com.vipul.dev.mypupilmesh.data.local.entity.UserEntity
import com.vipul.dev.mypupilmesh.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao:AppDao): UserRepository {
    override suspend fun saveUser(email: String, password: String) {
        userDao.insertUser(
            UserEntity(email=email, password = password)
        )
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
       return userDao.getUserByEmail(email)
    }

    override suspend fun userExists(email: String): Boolean {
        return userDao.getUserByEmail(email) != null
    }
}