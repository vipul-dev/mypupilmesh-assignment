package com.vipul.dev.mypupilmesh.data.repositoryImpl

import com.vipul.dev.mypupilmesh.data.local.UserDao
import com.vipul.dev.mypupilmesh.data.local.UserEntity
import com.vipul.dev.mypupilmesh.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao:UserDao): UserRepository {
    override suspend fun saveUser(email: String, password: String) {
        userDao.insertUser(
            UserEntity(email=email, password = password)
        )
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
       return userDao.getUserByEmail(email)
    }
}