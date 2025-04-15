package com.vipul.dev.mypupilmesh.di

import com.vipul.dev.mypupilmesh.data.local.UserDao
import com.vipul.dev.mypupilmesh.data.repositoryImpl.UserRepositoryImpl
import com.vipul.dev.mypupilmesh.domain.repository.UserRepository
import com.vipul.dev.mypupilmesh.domain.useCase.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository= UserRepositoryImpl(userDao)

    @Provides
    fun provideSaveUserUseCase(userRepository: UserRepository): SaveUserUseCase = SaveUserUseCase(userRepository)
}