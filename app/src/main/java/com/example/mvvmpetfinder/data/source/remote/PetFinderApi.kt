package com.example.mvvmpetfinder.data.source.remote

import com.example.mvvmpetfinder.data.model.Breed
import com.example.mvvmpetfinder.data.model.PetTypes
import com.example.mvvmpetfinder.data.model.Pets
import retrofit2.Call
import retrofit2.http.*

interface PetFinderApi {
    @GET("types")
    fun getTypes(): Call<PetTypes>

    @GET("animals")
    fun getPetsOfType(): Call<Pets>

    @GET("types/{type}/breeds")
    fun getBreeds(@Path("type") petType: String): Call<List<Breed>>

}