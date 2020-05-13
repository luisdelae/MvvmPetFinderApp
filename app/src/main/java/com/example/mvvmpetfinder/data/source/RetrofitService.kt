package com.example.mvvmpetfinder.data.source

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Handles service creation
 */
class RetrofitService {
    private val baseUrl = "https://api.petfinder.com/v2/"

    private val logging = HttpLoggingInterceptor()

    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Creates given service class a retrofit entity with no extra client headers
     */
    fun <S> createNoAuthService(serviceClass: Class<S>): S {
        val httpClient =
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        return getRetrofitBuilder(httpClient).build().create(serviceClass)
    }

    /**
     * Creates given service class a retrofit entity with auth token client header
     */
    // TODO: Add authenticator for auth errors
    fun <S> createAuthService(serviceClass: Class<S>, authToken: String): S {
        val httpClient =
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $authToken")
                    .build()
                    chain.proceed(request)
                }
                .addInterceptor(logging)
                .build()

        return getRetrofitBuilder(httpClient).build().create(serviceClass)
    }

    private fun getRetrofitBuilder(httpClient: OkHttpClient): Retrofit.Builder {
        val mapper = jacksonObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
    }
}