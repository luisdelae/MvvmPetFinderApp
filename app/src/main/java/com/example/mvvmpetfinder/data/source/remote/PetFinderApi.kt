package com.example.mvvmpetfinder.data.source.remote

import com.example.mvvmpetfinder.data.model.Breed
import com.example.mvvmpetfinder.data.model.PetTypes
import com.example.mvvmpetfinder.data.model.Pets
import retrofit2.Call
import retrofit2.http.*

interface PetFinderApi {
    @GET("types")
    fun getTypes(): Call<PetTypes>

    // https://github.com/square/retrofit/issues/3275
    @JvmSuppressWildcards
    @GET("animals")
    fun getPets(@QueryMap data: Map<String, Any>): Call<Pets>

    @GET("types/{type}/breeds")
    fun getBreeds(@Path("type") petType: String): Call<List<Breed>>

}