package com.example.calegplus

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreManager(@ApplicationContext val context: Context) {
    val getIsLogin: Flow<Boolean> = context.dataStore.data.map {
        it[IS_LOGIN_KEY] ?: false
    }
    val getUsername: Flow<String> = context.dataStore.data.map {
        it[USERNAME_KEY] ?: ""
    }
    val getId: Flow<Int> = context.dataStore.data.map {
        it[ID_KEY] ?: 0
    }

    suspend fun saveUsername(username: String) {
        context.dataStore.edit {
            it[USERNAME_KEY] = username
        }
    }
    suspend fun saveId(id: Int) {
        context.dataStore.edit {
            it[ID_KEY] = id
        }
    }
    suspend fun saveIsLoginStatus(paramIsLogin: Boolean) {
        context.dataStore.edit {
            it[IS_LOGIN_KEY] = paramIsLogin
        }
    }
    suspend fun removeIsLoginStatus() {
        context.dataStore.edit {
            it.remove(IS_LOGIN_KEY)
        }
    }
    suspend fun removeUsername() {
        context.dataStore.edit {
            it.remove(USERNAME_KEY)
        }
    }
    companion object {
        private const val DATASTORE_NAME = "user_preferences"
        private val IS_LOGIN_KEY = booleanPreferencesKey("is_login_key")
        private val USERNAME_KEY = stringPreferencesKey("username_key")
        private val ID_KEY = intPreferencesKey("id_key")
        private val Context.dataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}
