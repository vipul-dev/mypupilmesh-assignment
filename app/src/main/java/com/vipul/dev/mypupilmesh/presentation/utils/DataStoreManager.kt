package com.vipul.dev.mypupilmesh.presentation.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore by preferencesDataStore(name = "user_pref")

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        val IS_LOGIN = booleanPreferencesKey("is_login")
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { pref -> pref[IS_LOGIN] ?: false }

    suspend fun setLoginState(isLogIn: Boolean){
        context.dataStore.edit { pref->
            pref[IS_LOGIN]=isLogIn
        }
    }

    suspend fun clearLogin(){
        context.dataStore.edit { it.clear() }
    }

}