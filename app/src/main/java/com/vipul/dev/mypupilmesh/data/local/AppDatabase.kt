package com.vipul.dev.mypupilmesh.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}