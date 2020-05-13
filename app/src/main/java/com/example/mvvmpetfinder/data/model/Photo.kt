package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Photo(
    @JsonProperty("small")
    val small: String,
    @JsonProperty("medium")
    val medium: String,
    @JsonProperty("large")
    val large: String,
    @JsonProperty("full")
    val full: String
)