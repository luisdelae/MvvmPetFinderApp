package com.example.mvvmpetfinder.data.source.remote

import retrofit2.Call
import retrofit2.http.*

interface PetFinderApi {
    @GET("types")
    fun getTypes(@Header("Authorization: Bearer") accessToken: String) : Call<Any>
}