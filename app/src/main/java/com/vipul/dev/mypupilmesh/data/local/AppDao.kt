package com.vipul.dev.mypupilmesh.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vipul.dev.mypupilmesh.data.local.entity.MangaEntity
import com.vipul.dev.mypupilmesh.data.local.entity.UserEntity

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?



    // Manga Dao
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMangaAll(manga:List<MangaEntity>)

    @Query("SELECT * FROM manga_table")
    suspend fun getAllMangaData():List<MangaEntity>

    @Query("DELETE FROM manga_table")
    suspend fun clearAll()
}