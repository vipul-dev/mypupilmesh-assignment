package com.vipul.dev.mypupilmesh.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vipul.dev.mypupilmesh.data.remote.model.MangaData

@Entity(tableName = "manga_table")
data class MangaEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "sub_title") val subTitle: String,
    @ColumnInfo(name = "thumb") val thumb: String,
    @ColumnInfo(name = "summary") val summary: String,
) {
    fun toDomain() = MangaData(id, title, subTitle, thumb, summary)
}