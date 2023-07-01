package com.example.anothertodo

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.*

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response? {
        val authToken = ""
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", authToken)
        return chain.proceed(requestBuilder.build())
    }
}