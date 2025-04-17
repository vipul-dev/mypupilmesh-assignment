package com.vipul.dev.mypupilmesh.di

import android.content.Context
import androidx.room.Room
import com.vipul.dev.mypupilmesh.data.local.AppDatabase
import com.vipul.dev.mypupilmesh.data.local.AppDao
import com.vipul.dev.mypupilmesh.networkObserver.NetworkObserverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java,"app_database").build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): AppDao = db.appDao()

    @Provides
    @Singleton
    fun provideNetworkObserver(@ApplicationContext context: Context): NetworkObserverImpl{
        return NetworkObserverImpl(context)
    }

}