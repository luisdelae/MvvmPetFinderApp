package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonProperty

class Video(
    @JsonProperty("embed")
    val embededVideo: String
)