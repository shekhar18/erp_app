package com.techcognics.erpapp.data.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.datastore by preferencesDataStore(name = "session_prefs")

@Singleton
class SessionManager @Inject constructor(private val context: Context) {

    private val authTokenKey = stringPreferencesKey("auth_token")


    suspend fun saveAuthToken(token: String) {
        context.datastore.edit { pref ->
            pref[authTokenKey] = token
        }
    }

    fun getAuthToken(): Flow<String?> {
        return context.datastore.data.map { pref ->
            pref[authTokenKey]
        }
    }

    suspend fun clearSession() {
        context.datastore.edit { it.clear() }
    }

}