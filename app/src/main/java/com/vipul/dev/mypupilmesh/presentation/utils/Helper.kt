package com.vipul.dev.mypupilmesh.presentation.utils

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


val json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    classDiscriminator = "type"
}


val navItems = listOf(
    NavItem(label = "Manga", route = "manga"),
    NavItem(label = "Face", route = "face"),
)

@Serializable
data class NavItem(
    val label: String, val route: String
)

enum class NetworkStatus{
    Available,Unavailable,Losing,Lost
}