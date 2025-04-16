package com.vipul.dev.mypupilmesh.presentation.utils

import com.vipul.dev.mypupilmesh.presentation.ui.screens.dashboard.NavItem
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


val json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    classDiscriminator = "type"
}

inline fun <reified T> T.toRoute(): String = json.encodeToString(serializer(), this)
inline fun <reified T> String.toDest(): String = json.decodeFromString(serializer(), this)

val navItems = listOf(
    NavItem(label = "Manga", route = "manga"),
    NavItem(label = "Face", route = "face"),
)