package com.example.mvvmpetfinder.data.source

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Handles service creation
 */
class RetrofitService {
    private val baseUrl = "https://api.petfinder.com/v2/"

    fun <S> createNoAuthService(serviceClass: Class<S>): S {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient =
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())


        return retrofit.build().create(serviceClass)
    }

    fun <S> createAuthService(serviceClass: Class<S>): S {
        val token = ""
        val httpClient =
            OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())

        return retrofit.build().create(serviceClass)
    }
}