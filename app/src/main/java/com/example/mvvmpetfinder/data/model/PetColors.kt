package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class PetColors(
    @SerializedName("primary")
    val primary: String?,
    @SerializedName("secondary")
    val secondary: String?,
    @SerializedName("tertiary")
    val tertiary: String?
)