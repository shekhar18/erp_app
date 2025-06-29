package com.techcognics.erpapp.data.network.interceptor

import android.util.Log
import com.techcognics.erpapp.data.session.SessionManager
import jakarta.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor @Inject constructor(val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val t1 = System.nanoTime()
        Log.d("API_REQUEST", "Sending request: ${request.url} \n${request.headers}")
        val response = chain.proceed(request)

        val t2 = System.nanoTime()

        Log.d(
            "API_RESPONSE",
            "Received response for ${response.request.url} in ${(t2 - t1) / 1e6}ms"
        )
        Log.d("API_RESPONSE_BODY", response.peekBody(1024 * 1024).string())


        /*when (response.code) {
            401 -> CoroutineScope(Dispatchers.IO).launch {
                sessionManager.clearSession()

            }
            500 -> Log.e("API_ERROR", "Server error - Try again later")
            in 400..499 -> Log.e("API_ERROR", "Client error: ${response.code}")
        }
*/
        return response
    }
}