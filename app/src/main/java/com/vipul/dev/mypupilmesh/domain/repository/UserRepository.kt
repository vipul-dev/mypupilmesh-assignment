package com.vipul.dev.mypupilmesh.domain.repository

import com.vipul.dev.mypupilmesh.data.local.UserEntity

interface UserRepository {
    suspend fun saveUser(email: String,password: String)
    suspend fun getUserByEmail(email: String): UserEntity?
}