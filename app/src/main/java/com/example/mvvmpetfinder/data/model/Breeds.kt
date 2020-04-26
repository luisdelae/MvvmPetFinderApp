package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class Breeds(
    @SerializedName("primary")
    val primary: String?,
    @SerializedName("secondary")
    val secondary: String?,
    @SerializedName("mixed")
    val mixed: Boolean,
    @SerializedName("unknown")
    val unknown: Boolean
)