package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class Address(
    @SerializedName("address1")
    val addressLine1: String,
    @SerializedName("address2")
    val addressLine2: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("postcode")
    val postcode: String,
    @SerializedName("country")
    val country: String
)