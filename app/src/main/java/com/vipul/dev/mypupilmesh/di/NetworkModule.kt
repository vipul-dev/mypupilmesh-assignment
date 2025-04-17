package com.vipul.dev.mypupilmesh.di

import com.vipul.dev.mypupilmesh.data.remote.MangaApiServices
import com.vipul.dev.mypupilmesh.presentation.utils.ConstantUtils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MangaApiServices{
        return retrofit.create(MangaApiServices::class.java)
    }

}