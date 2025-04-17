package com.vipul.dev.mypupilmesh.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.vipul.dev.mypupilmesh.data.local.entity.MangaEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MangaData(
    @SerializedName("id") val id: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("sub_title") val subTitle: String? = null,
    @SerializedName("thumb") val thumb: String? = null,
    @SerializedName("summary") val summary: String? = null,
) : Parcelable {
    fun toDomain() = MangaData(id, title, subTitle, thumb, summary)

    fun toEntity()= MangaEntity(id!!,title!!,subTitle!!,thumb!!,summary!!)
}
