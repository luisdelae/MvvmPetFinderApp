package com.example.mvvmpetfinder.data.source.remote

import com.example.mvvmpetfinder.data.model.Breed
import com.example.mvvmpetfinder.data.model.PetTypes
import retrofit2.Call
import retrofit2.http.*

interface PetFinderApi {
    @GET("types")
    fun getTypes() : Call<PetTypes>

    @GET("types/{type}/breeds")
    fun getBreeds(@Path("type") petType: String): Call<List<Breed>>

}