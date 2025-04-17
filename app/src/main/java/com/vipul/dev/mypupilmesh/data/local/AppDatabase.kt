package com.vipul.dev.mypupilmesh.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.vipul.dev.mypupilmesh.data.local.entity.MangaEntity
import com.vipul.dev.mypupilmesh.data.local.entity.UserEntity

@Database(entities = [UserEntity::class, MangaEntity::class], autoMigrations = [AutoMigration(from = 1, to = 2)], exportSchema = true, version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}