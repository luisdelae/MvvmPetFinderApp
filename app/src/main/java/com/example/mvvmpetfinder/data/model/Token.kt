package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class Token(
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("access_token")
    val accessToken: String
)