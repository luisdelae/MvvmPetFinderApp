package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class PetTypes(
    @SerializedName("types")
    val types: ArrayList<PetType>
)

class PetType(
    @SerializedName("name")
    val name: String,
    @SerializedName("coats")
    val coats: List<String>,
    @SerializedName("colors")
    val colors: List<String>,
    @SerializedName("genders")
    val genders: List<String>
)