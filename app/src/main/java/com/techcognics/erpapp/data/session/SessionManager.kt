package com.techcognics.erpapp.data.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.datastore by preferencesDataStore(name = "session_prefs")

@Singleton
class SessionManager @Inject constructor(private val context: Context) {

    private val authTokenKey = stringPreferencesKey("auth_token")
    private val userRoleListKey = stringPreferencesKey("role_list")

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

    suspend fun saveUserRoles(userRoleList: List<String>) {
        val jsonString = Gson().toJson(userRoleList)
        context.datastore.edit { pref ->
            pref[userRoleListKey] = jsonString
        }
    }


    suspend fun getUserRoles(): List<String> {
        val jsonString = context.datastore.data.first()[userRoleListKey].toString()
        return if (jsonString.isNotEmpty()) {
            Gson().fromJson(jsonString, object : TypeToken<List<String>>() {}.type)
        } else {
            emptyList<String>()
        }
    }

}