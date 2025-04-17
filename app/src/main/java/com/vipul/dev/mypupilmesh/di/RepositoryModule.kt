package com.vipul.dev.mypupilmesh.di

import com.vipul.dev.mypupilmesh.data.local.AppDao
import com.vipul.dev.mypupilmesh.data.remote.MangaApiServices
import com.vipul.dev.mypupilmesh.data.repositoryImpl.MangaRepositoryImpl
import com.vipul.dev.mypupilmesh.data.repositoryImpl.UserRepositoryImpl
import com.vipul.dev.mypupilmesh.domain.repository.MangaRepository
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
    fun provideUserRepository(userDao: AppDao): UserRepository= UserRepositoryImpl(userDao)

    @Provides
    fun provideSaveUserUseCase(userRepository: UserRepository): SaveUserUseCase = SaveUserUseCase(userRepository)

    @Provides
    @Singleton
    fun provideMangaRepository(mangaApi: MangaApiServices,appDao: AppDao): MangaRepository = MangaRepositoryImpl(mangaApi,appDao)
}