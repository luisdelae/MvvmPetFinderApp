package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class LocationFilter(
    @JsonProperty("location")
    val zipCode: String,
    @JsonProperty("distance")
    val distanceMiles: Int
)