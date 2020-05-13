package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonProperty

class Token(
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("expires_in")
    val expiresIn: Int,
    @JsonProperty("access_token")
    val accessToken: String
)