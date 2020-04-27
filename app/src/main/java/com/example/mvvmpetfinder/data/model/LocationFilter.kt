package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class LocationFilter(
    @SerializedName("location")
    val zipCode: String,
    @SerializedName("distance")
    val distanceMiles: Int
)