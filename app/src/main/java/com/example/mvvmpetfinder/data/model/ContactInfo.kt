package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class ContactInfo(
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("address")
    val address: Address
)